package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class Parser {
    private String title;
    private Document doc;


    public Parser(String url) {
        try {
            doc = Jsoup.connect(url).get();
            this.title = doc.title();

        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println("for url " + url);
            System.out.println(e.getMessage());
            title = url;
            this.title = url;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getURLString() {
        try {
            Element body = doc.body();
            return body.wholeText();
        } catch (Exception e) {
            System.out.println("Exception at reading : " + doc.baseUri());
            return "Exception at reading : " + doc.baseUri();
        }
    }

    public HashMap<String, String> getSubURL() {
        Elements links = doc.select("a[href]");
        HashMap<String, String> hashMap = new HashMap<>();
        for (Element link : links) {
            hashMap.put(link.attr("abs:href"), link.text());

        }
        return hashMap;
    }


    @Override
    public String toString() {
        return title;
    }
}
