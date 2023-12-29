package test.task.vacancyparser.util;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;
import test.task.vacancyparser.model.Job;

@Component
public class CsvWriter {
    public static void writeJobsToCsv(List<Job> jobs, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(new File(filePath)))) {
            writer.writeNext(new String[]{"id", "jobUrl", "positionName", "organizationUrl", "logo",
                    "organizationTitle", "laborFunction", "location", "postedDate", "tags", "description"});

            for (Job job : jobs) {
                writer.writeNext(new String[]{
                        String.valueOf(job.getId()), job.getJobUrl(), job.getPositionName(), job.getOrganizationUrl(),
                        job.getLogo(), job.getOrganizationTitle(), job.getLaborFunction(), job.getLocation(),
                        String.valueOf(job.getPostedDate()), job.getTags(), job.getDescription()
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + filePath, e);
        }
    }
}