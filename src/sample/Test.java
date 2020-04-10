package sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;



public class Test {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Heath").get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.getElementsByTag("p");
        for (Element headline : newsHeadlines){
            System.out.println(headline.attr("title")+"$$"+ headline.absUrl("href"));
        }

    }

}
