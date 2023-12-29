package test.task.vacancyparser.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.vacancyparser.service.JobService;
import test.task.vacancyparser.dto.VacancyDto;

@RestController
@RequiredArgsConstructor
public class VacancyController {
    private final JobService jobService;

    @GetMapping("/api/vacancies")
    public List<VacancyDto> getVacancyByFunction(@RequestParam String jobFunction) {
        return jobService.getVacanciesByFunction(jobFunction);
    }
}
