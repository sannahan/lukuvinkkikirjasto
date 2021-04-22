package dao;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapAutotagDao implements AutotagDao {
    private Map<String, String> mappaukset;

    public HashMapAutotagDao() {
        mappaukset = Stream.of(new String[][] {
            { "youtube", "video" }, 
            { "dl.acm.org", "artikkeli" }, 
            { "is.fi", "artikkeli" },
            { "hs.fi", "artikkeli" },
          }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    @Override
    public Map<String, String> haeMappaukset() {
        return this.mappaukset;
    }
}
