package VIEWS;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class getTodayWeather {
    public String getTodayWeather() {
        String weather = "无";
        try {
            Document doc = Jsoup.connect("http://www.weather.com.cn/weather/101200601.shtml").get();
            weather = doc.select("#7d > ul > li.sky.skyid.lv2.on > p.wea").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }
}
