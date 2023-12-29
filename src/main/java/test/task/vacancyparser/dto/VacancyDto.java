package test.task.vacancyparser.dto;

import java.util.List;

public record VacancyDto(
    JobItem jobItem,
    List<String> tags
){
}
