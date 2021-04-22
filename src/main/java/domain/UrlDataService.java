package domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlDataService {
    public UrlDataService() {

    }

    public String getOtsikko(String url) {
        Document document;
        String htmlOtsikko = "";

        try {
            document = Jsoup.connect(url).get();
            htmlOtsikko = document.title();
        } catch (Throwable e) {
            // e.printStackTrace();
        }

        System.out.println("Otsikko: " + htmlOtsikko);
        return htmlOtsikko;
    }
}
