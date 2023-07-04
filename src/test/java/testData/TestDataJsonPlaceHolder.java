package testData;
import org.json.JSONObject;
import java.util.HashMap;
public class TestDataJsonPlaceHolder {

    public int basariliStatusCode = 200;
    public String contentType = "application/json; charset=utf-8";
    public String connectionHeaderDegeri = "keep-alive";

    public JSONObject expectedBodyOlusturJson(){
        JSONObject expBody=new JSONObject();
        expBody.put("userId",3);
        expBody.put("id",22);
        expBody.put("title","dolor sint quo a velit explicabo quia nam");
        expBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");
        return expBody;
    }

    public JSONObject requestBodyOlusturJSON(){

        /*  Request Body
        {
            "title":"Ali",
                "body":"Merhaba",
                "userId":10,
                "id":70
        } */
        JSONObject reqBody = new JSONObject();
        reqBody.put("title", "Ali");
        reqBody.put("body", "Merhaba");
        reqBody.put("userId", 10);
        reqBody.put("id", 70);


        return reqBody;
    }

    public HashMap requestBodyOlusturMap() {

        HashMap<String, Object> reqBody = new HashMap<>();

        reqBody.put("title", "Ahmet");
        reqBody.put("body", "Merhaba");
        reqBody.put("userId", 10.0);
        reqBody.put("id", 70.0);

        return reqBody;
    }


}
