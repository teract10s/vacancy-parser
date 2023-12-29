package test.task.vacancyparser.techstarsjobs;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import test.task.vacancyparser.dto.JobItem;
import test.task.vacancyparser.dto.VacancyDto;
import test.task.vacancyparser.exception.UrlConnectionException;
import test.task.vacancyparser.util.Base64Encoder;

@Component
public class TechstarsJobsScraper {
    @Autowired
    private JobVacancyScraper jobVacancyScraper;
    private static final String BASE_URL = "https://jobs.techstars.com";
    private static final String FUNCTION_FILTER_PATTERN = "{\"job_functions\":[%s]}";
    @Value("${vacancy.url.class}")
    private String vacancyUrlClass;
    @Value("${vacancy.class}")
    private String vacancyClass;
    @Value("${tag.class}")
    private String tagClass;

    public List<VacancyDto> retrieveVacanciesByFunction(String jobFunction) {
        String url = buildUrlByFunction(jobFunction);
        List<Element> vacanciesContent = fetchVacanciesContent(url);
        return parseHtmlVacancies(vacanciesContent);
    }

    private List<VacancyDto> parseHtmlVacancies(List<Element> vacanciesContent) {
       return vacanciesContent.parallelStream()
                .filter(v -> v.select(vacancyUrlClass).attr("href").startsWith("/"))
                .map(v -> {
                    List<String> tags = v.select(tagClass).stream()
                            .map(Element::text)
                            .toList();
                    String url = v.select(vacancyUrlClass).attr("href");
                    JobItem jobItem = jobVacancyScraper.retrieveJobItemByVacancyUrl(BASE_URL + url);
                    return new VacancyDto(jobItem, tags);
                })
                .toList();
    }

    private List<Element> fetchVacanciesContent(String url) {
        try {
            var document = Jsoup.connect(url).get();
            return document.select(vacancyClass);
        } catch (IOException e) {
            throw new UrlConnectionException("Can't connect to url: " + url, e);
        }
    }

    private String buildUrlByFunction(String function) {
        String filters = String.format(FUNCTION_FILTER_PATTERN, '"' + function + '"');
        String encodedFilters = Base64Encoder.encodeString(filters);
        return String.format(BASE_URL + "/jobs?filter=%s", encodedFilters);
    }
}
