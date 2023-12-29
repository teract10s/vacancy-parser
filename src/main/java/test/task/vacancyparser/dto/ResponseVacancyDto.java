package test.task.vacancyparser.dto;

import java.util.List;

public record ResponseVacancyDto (
    JobItem jobItem,
    List<String> tags
){
}
