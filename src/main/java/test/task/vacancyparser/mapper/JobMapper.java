package test.task.vacancyparser.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
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

    @Mapping(target = "jobItem.url", source = "jobUrl")
    @Mapping(target = "jobItem.positionName", source = "positionName")
    @Mapping(target = "jobItem.organizationUrl", source = "organizationUrl")
    @Mapping(target = "jobItem.logo", source = "logo")
    @Mapping(target = "jobItem.organizationTitle", source = "organizationTitle")
    @Mapping(target = "jobItem.laborFunction", source = "laborFunction")
    @Mapping(target = "jobItem.location", source = "location")
    @Mapping(target = "jobItem.description", source = "description")
    VacancyDto toDto(Job job);

    @AfterMapping
    default void mapDateFields(VacancyDto vacancyDto,
                               @MappingTarget Job job) {
        LocalDateTime postedDate = vacancyDto.getJobItem().getPostedDate().atTime(0, 0);
        job.setPostedDate(Timestamp.valueOf(postedDate));
    }

    @AfterMapping
    default void mapDateFields(Job job,
                               @MappingTarget VacancyDto vacancyDto) {
        vacancyDto.getJobItem().setPostedDate(job.getPostedDate().toLocalDateTime().toLocalDate());
    }

    default String mapTags(List<String> tags) {
        return tags != null ? String.join(",", tags) : "NOT_FOUND";
    }

    default List<String> mapTags(String tags) {
        return Arrays.stream(tags.split(",")).toList();
    }
}
