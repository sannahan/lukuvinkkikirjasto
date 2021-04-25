package domain;

import java.util.regex.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlDataServiceImpl implements UrlDataService{
    public UrlDataServiceImpl() {

    }

    @Override
    public String getOtsikko(String url) {
        Document document;
        String htmlOtsikko = "";

        if (!onkoValidiURL(url)) {
            return htmlOtsikko;
        }

        if (!onkoAlussaHTTP(url)) {
            url = "http://" + url;
        }

        try {
            document = Jsoup.connect(url).get();
            htmlOtsikko = document.title();
        } catch (Throwable e) {
            // e.printStackTrace();
        }

        System.out.println("Otsikko: " + htmlOtsikko);
        return htmlOtsikko;
    }

    private boolean onkoValidiURL(String url) {
        return url.matches("^((http|https):\\/\\/)?(w{3}.)?([a-zA-Z0-9]+).([a-z]{2,3})");
    }

    private boolean onkoAlussaHTTP(String url) {
        return url.matches("^(http|https)://(.)*");
    }
}
