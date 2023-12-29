package test.task.vacancyparser;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import test.task.vacancyparser.util.Base64Encoder;

public class Main {
    public static void main(String[] args) {
        try {
            String functionPattern = "{\"job_functions\":[%s]}";
            String jobFunction = "\"asdf\"";
            String filterFunction = Base64Encoder.encodeString(String.format(functionPattern, jobFunction));
            String url = "https://jobs.techstars.com/jobs?filter=%s";
            System.out.println(String.format(url, filterFunction));
            var document = Jsoup.connect(String.format(url, filterFunction)).get();

            var elements = document.select(".sc-beqWaB.kToBwF");
            for (Element element : elements) {
                System.out.println("Title: " + element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
