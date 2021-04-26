package domain;

import java.util.HashMap;

public class UrlDataServiceStub implements UrlDataService{
    private HashMap<String, String> headers;
    public UrlDataServiceStub() {
        this.headers = new HashMap<>();
        this.headers.put("muu.com", "Muu");
        this.headers.put("maa.com", "Maa");
        this.headers.put("miu.com", "Miu");
        this.headers.put("www.hs.fi", "Uutisaet | HS.fi");
        this.headers.put("http://www.youtube.com", "YouTube");
    }
    @Override
    public String getOtsikko(String url) {
        return this.headers.getOrDefault(url, "Testi otsikko");
    }
}
