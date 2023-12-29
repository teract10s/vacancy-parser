package test.task.vacancyparser.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobItem {
    private String url;
    private String positionName;
    private String organizationUrl;
    private String logo;
    private String organizationTitle;
    private String laborFunction;
    private String location;
    private LocalDate postedDate;
    private String description;
}
