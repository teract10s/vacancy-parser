package test.task.vacancyparser.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.task.vacancyparser.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    @Transactional
    @Modifying
    @Query("UPDATE Job j SET j.positionName = :#{#job.positionName}, j.organizationUrl = :#{#job.organizationUrl}, " +
            "j.logo = :#{#job.logo}, j.organizationTitle = :#{#job.organizationTitle}, j.laborFunction = :#{#job.laborFunction}, " +
            "j.location = :#{#job.location}, j.postedDate = :#{#job.postedDate}, j.tags = :#{#job.tags}, " +
            "j.description = :#{#job.description} WHERE j.jobUrl = :url")
    void updateByJobUrl(@Param("url") String url, @Param("job") Job job);

    Optional<Job> findByJobUrl(String url);
}
