package test.task.vacancyparser.dto;

public record JobSearchParameters(
        String[] positionNames,
        String[] organizationTitles,
        String[] laborFunctions,
        String[] locations
) {
}
