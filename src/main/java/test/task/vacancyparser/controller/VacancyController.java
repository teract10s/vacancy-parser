package test.task.vacancyparser.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.task.vacancyparser.techstarsjobs.TechstarsJobsScraper;
import test.task.vacancyparser.dto.ResponseVacancyDto;

@RestController
@RequiredArgsConstructor
public class VacancyController {
    private final TechstarsJobsScraper techstarsJobsScraper;

    @GetMapping("/api/vacancies")
    public List<ResponseVacancyDto> getVacancyByFunction(@RequestParam String jobFunction) {
        return techstarsJobsScraper.retrieveVacanciesByFunction(jobFunction);
    }
}
