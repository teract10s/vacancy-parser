package test.task.vacancyparser.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.task.vacancyparser.dto.VacancyDto;
import test.task.vacancyparser.mapper.JobMapper;
import test.task.vacancyparser.model.Job;
import test.task.vacancyparser.repository.JobRepository;
import test.task.vacancyparser.techstarsjobs.TechstarsJobsScraper;
import test.task.vacancyparser.util.CsvWriter;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final TechstarsJobsScraper techstarsJobsScraper;
    private final ExecutorService executorService;
    @Value("${result.csv.file.path}")
    private String csvFilePath;

    public List<VacancyDto> getVacanciesByFunction(String jobFunction) {
        List<VacancyDto> vacancies = techstarsJobsScraper.retrieveVacanciesByFunction(jobFunction);
        List<Job> savedJobs = saveJobsByVacancyDto(vacancies);
        executorService.execute(
                () -> CsvWriter.writeJobsToCsv(savedJobs, csvFilePath)
        );
        return vacancies;
    }

    public List<Job> saveJobsByVacancyDto(List<VacancyDto> vacancies) {
        List<Job> jobs = vacancies.stream()
                .map(jobMapper::toJob)
                .toList();

        Map<Boolean, List<Job>> partitionedJobs = jobs.stream()
                .collect(Collectors.partitioningBy(job -> jobRepository.findByJobUrl(job.getJobUrl()).isPresent()));

        List<Job> updatedJobs = partitionedJobs.get(true).stream()
                .map(job -> {
                    jobRepository.updateByJobUrl(job.getJobUrl(), job);
                    return jobRepository.findByJobUrl(job.getJobUrl()).get();
                })
                .toList();

        List<Job> result = jobRepository.saveAll(partitionedJobs.get(false));
        result.addAll(updatedJobs);
        return result;
    }
}
