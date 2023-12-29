package test.task.vacancyparser.dto;

import java.time.LocalDate;

public record JobItem(
        String url,
        String positionName,
        String organizationUrl,
        String logo,
        String organizationTitle,
        String laborFunction,
        String location,
        LocalDate postedDate,
        String description
) {
}
