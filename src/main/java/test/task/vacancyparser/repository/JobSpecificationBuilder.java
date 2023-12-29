package test.task.vacancyparser.repository;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import test.task.vacancyparser.dto.JobSearchParameters;
import test.task.vacancyparser.model.Job;

@Component
public class JobSpecificationBuilder {
    public Specification<Job> buildFrom(JobSearchParameters searchParameters) {
        Specification<Job> spec = Specification.where(null);
        if (searchParameters.positionNames() != null && searchParameters.positionNames().length > 0) {
            spec = spec.and(getPostionSpecification(searchParameters.positionNames()));
        }
        if (searchParameters.organizationTitles() != null && searchParameters.organizationTitles().length > 0) {
            spec = spec.and(getOrganizationSpecification(searchParameters.organizationTitles()));
        }
        if (searchParameters.laborFunctions() != null && searchParameters.laborFunctions().length > 0) {
            spec = spec.and(getFunctionSpecification(searchParameters.laborFunctions()));
        }
        if (searchParameters.locations() != null && searchParameters.locations().length > 0) {
            spec = spec.and(getLocationsSpecification(searchParameters.locations()));
        }
        return spec;
    }

    private Specification<Job> getPostionSpecification(String[] positions) {
        return (root, query, criteriaBuilder) -> root.get("positionName")
                .in(Arrays.stream(positions).toArray());
    }

    private Specification<Job> getOrganizationSpecification(String[] organizationTitles) {
        return (root, query, criteriaBuilder) -> root.get("organizationTitle")
                .in(Arrays.stream(organizationTitles).toArray());
    }

    private Specification<Job> getFunctionSpecification(String[] laborFunctions) {
        return (root, query, criteriaBuilder) -> root.get("laborFunction")
                .in(Arrays.stream(laborFunctions).toArray());
    }

    private Specification<Job> getLocationsSpecification(String[] locations) {
        return (root, query, criteriaBuilder) -> root.get("location")
                .in(Arrays.stream(locations).toArray());
    }
}
