package test.task.vacancyparser.techstarsjobs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.task.vacancyparser.dto.JobItem;
import test.task.vacancyparser.exception.UrlConnectionException;

@Component
public class JobVacancyScraper {
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
        return getJobItemByDocument(url, fetchedVacancy);
    }

    private JobItem getJobItemByDocument(String url, Document fetchedVacancy) {
        String positionName = fetchedVacancy.select(positionClass).text();
        String organizationUrl = fetchedVacancy.select(organizationUrlClass).attr("href");
        String logo = fetchedVacancy.select(organizationLogoClass).attr("src");
        String organizationTitle = fetchedVacancy.select(organizationTitleClass).text();
        String laborFunction = fetchedVacancy.select(laborFunctionClass).stream()
                .skip(4)
                .findFirst()
                .get()
                .text();
        String address = fetchedVacancy.select(addressClass).stream()
                .skip(5)
                .findFirst()
                .get()
                .text();
        Date date = parsePostedDate(fetchedVacancy.select(postedDateClass).text());
        String description = fetchedVacancy.select(descriptionClass).html();
        return new JobItem(url, positionName, organizationUrl,
                logo, organizationTitle, laborFunction,
                address, date, description);
    }

    private Date parsePostedDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("'Posted on' EEEE, MMMM dd, yyyy", Locale.ENGLISH);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
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
