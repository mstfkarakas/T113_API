package test;
import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL {
        /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data :
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
     */

    @Test
    public void put01() {

        // 1) URL ve request body hazirla
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        HashMap<String, Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        // 2) Expected data olustur
        HashMap<String, Object> expData = testDataJsonPlaceHolder.requestBodyOlusturMap(); // aslinda expData yukardaki reqBody ile ayni.
                                                                                // Kafa karismasin diye bu sekilde tekrar kaydetmis olduk.


        // 3) Response'u kaydet
                        //toString ile Json objesini Java objesine cevirmek zorundayiz.
                        // Map'lerde buna gerek yok, cunku Map zaten Java objesidir.

        Response response = given().spec(specJsonPlace).contentType(ContentType.JSON).when().body(reqBody).put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4) Assertion
        //expData'miz HashMap idi. response'i da HashMap'e cevirmemiz ve kaydetmemiz lazim.

        HashMap<String, Object> respMap = response.as(HashMap.class);

        assertEquals(expData.get("title"), respMap.get("title"));
        assertEquals(expData.get("body"), respMap.get("body"));
        assertEquals(expData.get("userId"), respMap.get("userId"));
        assertEquals(expData.get("id"), respMap.get("id"));



    }
}
