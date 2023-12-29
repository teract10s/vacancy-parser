package test.task.vacancyparser.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.vacancyparser.dto.JobSearchParameters;
import test.task.vacancyparser.service.JobService;
import test.task.vacancyparser.dto.VacancyDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final JobService jobService;

    @GetMapping
    public List<VacancyDto> getVacancyByFunction(@RequestParam String jobFunction) {
        return jobService.getVacanciesByFunction(jobFunction);
    }

    @GetMapping("/search")
    public List<VacancyDto> search(JobSearchParameters searchParameters) {
        return jobService.search(searchParameters);
    }
}
