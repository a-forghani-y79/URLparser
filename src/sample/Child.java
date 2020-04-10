package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Child {
    String title;
    String url;
    Document doc;

    public Child(String url) throws IOException {
        this.url = url;
        doc = Jsoup.connect(url).get();
        this.title = doc.title();


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getURLString() {
        return null;
    }

    @Override
    public String toString() {
        return url;
    }
}
