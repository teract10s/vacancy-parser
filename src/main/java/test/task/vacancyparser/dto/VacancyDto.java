package test.task.vacancyparser.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyDto{
    private JobItem jobItem;
    private List<String> tags;
}
