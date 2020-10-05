package VIEWS;



import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class getSentence {
    public String getSentence(){
        JSONObject object = null;
        try{
            Document doc = Jsoup.connect("http://open.iciba.com/dsapi/").get();
            object = JSONObject.fromObject(doc.body().text());

        }catch (Exception e){
            e.printStackTrace();
        }
        return object.getString("note");
    }
}
