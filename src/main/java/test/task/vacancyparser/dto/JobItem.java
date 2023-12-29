package test.task.vacancyparser.dto;

import java.util.Date;

public record JobItem(
        String url,
        String positionName,
        String organizationUrl,
        String logo,
        String organizationTitle,
        String laborFunction,
        String address,
        Date pastedDate,
        String description
) {
}
