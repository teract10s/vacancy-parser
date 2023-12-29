package test.task.vacancyparser.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.vacancyparser.dto.JobSearchParameters;
import test.task.vacancyparser.service.JobService;
import test.task.vacancyparser.dto.VacancyDto;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Vacancy management", description = "Endpoints for managing vacancies")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final JobService jobService;

    @GetMapping
    @Operation(summary = "Get vacancies", description = "Get vacancies by specific job function")
    public List<VacancyDto> getVacancyByFunction(@RequestParam String jobFunction) {
        return jobService.getVacanciesByFunction(jobFunction);
    }

    @GetMapping("/search")
    @Operation(summary = "Search vacancies", description = "Find specific jobs in the database by parameters")
    public List<VacancyDto> search(JobSearchParameters searchParameters) {
        return jobService.search(searchParameters);
    }
}
