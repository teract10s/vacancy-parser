package test.task.vacancyparser.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import test.task.vacancyparser.config.MapperConfig;
import test.task.vacancyparser.dto.VacancyDto;
import test.task.vacancyparser.model.Job;

@Mapper(config = MapperConfig.class)
public interface JobMapper {
    @Mapping(source = "jobItem.url", target = "jobUrl")
    @Mapping(source = "jobItem.positionName", target = "positionName")
    @Mapping(source = "jobItem.organizationUrl", target = "organizationUrl")
    @Mapping(source = "jobItem.logo", target = "logo")
    @Mapping(source = "jobItem.organizationTitle", target = "organizationTitle")
    @Mapping(source = "jobItem.laborFunction", target = "laborFunction")
    @Mapping(source = "jobItem.location", target = "location")
    @Mapping(source = "jobItem.description", target = "description")
    Job toJob(VacancyDto dto);

    @AfterMapping
    default void mapDateFields(VacancyDto vacancyDto,
                               @MappingTarget Job job) {
        LocalDateTime postedDate = vacancyDto.jobItem().postedDate().atTime(0, 0);
        job.setPostedDate(Timestamp.valueOf(postedDate));
    }

    default String mapTags(List<String> tags) {
        return tags != null ? String.join(",", tags) : "NOT_FOUND";
    }
}
