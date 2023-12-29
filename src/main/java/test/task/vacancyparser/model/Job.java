package test.task.vacancyparser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "jobs")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "job_url", columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String jobUrl;
    @Column(name = "position_name", columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String positionName;
    @Column(name = "organization_url", columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String organizationUrl;
    @Column(columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String logo;
    @Column(name = "organization_title", columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String organizationTitle;
    @Column(name = "labor_function", columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String laborFunction;
    @Column(columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String location;
    @Column(name = "posted_date")
    private Timestamp postedDate;
    @Column(columnDefinition = "varchar(255) default 'NOT_FOUND'")
    private String tags;
    @Column(columnDefinition = "LONGTEXT")
    @ToString.Exclude
    private String description;
}
