package test.task.vacancyparser.techstarsjobs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.task.vacancyparser.dto.JobItem;
import test.task.vacancyparser.exception.UrlConnectionException;

@Component
public class JobVacancyScraper {
    private static final String DATE_FORMAT = "'Posted on' EEEE, MMMM dd, yyyy";
    @Value("${position.class}")
    private String positionClass;
    @Value("${organization.url.class}")
    private String organizationUrlClass;
    @Value("${organization.logo.class}")
    private String organizationLogoClass;
    @Value("${organization.title.class}")
    private String organizationTitleClass;
    @Value("${labor.function.class}")
    private String laborFunctionClass;
    @Value("${address.class}")
    private String addressClass;
    @Value("${posted.date.class}")
    private String postedDateClass;
    @Value("${description.class}")
    private String descriptionClass;

    public JobItem retrieveJobItemByVacancyUrl(String url) {
        Document fetchedVacancy = fetchDocumentByUrl(url);
        return getJobItemByFetchedVacancy(url, fetchedVacancy);
    }

    private JobItem getJobItemByFetchedVacancy(String url, Document vacancy) {
        String positionName = vacancy.select(positionClass).text();
        String organizationUrl = vacancy.select(organizationUrlClass).attr("href");
        String logo = vacancy.select(organizationLogoClass).attr("src");
        String organizationTitle = vacancy.select(organizationTitleClass).stream()
                .skip(3)
                .findFirst()
                .get()
                .text();
        String laborFunction = vacancy.select(laborFunctionClass).stream()
                .skip(4)
                .findFirst()
                .get()
                .text();
        String address = vacancy.select(addressClass).stream()
                .skip(5)
                .findFirst()
                .get()
                .text();
        LocalDate date = parsePostedDate(vacancy.select(postedDateClass).text());
        String description = vacancy.select(descriptionClass).html();
        return new JobItem(url, positionName, organizationUrl,
                logo, organizationTitle, laborFunction,
                address, date, description);
    }

    private LocalDate parsePostedDate(String dateString) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
        try {
            return LocalDate.parse(dateString, dateFormat);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse: " + dateString + " to date");
        }
    }

    private Document fetchDocumentByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new UrlConnectionException("Can't connect to url: " + url, e);
        }
    }
}
