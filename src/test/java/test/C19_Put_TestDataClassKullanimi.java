package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {

     /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in
    status kodunun 200, content type’inin "application/json; charset=utf-8",
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data
        {
        "title":"Ali",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
  */

    @Test
    public void put01(){
        // 1) URL ve request body hazirla
        specJsonPlace.pathParams("pp1", "posts", "pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        JSONObject reqBody = testDataJsonPlaceHolder.requestBodyOlusturJSON();

        // 2) Expected data olustur
        JSONObject expData = testDataJsonPlaceHolder.requestBodyOlusturJSON(); // reqBody ile ayni sey, kopyaladik ama gerek yok aslinda.



        // 3) Response'u kaydet
        Response response = given().spec(specJsonPlace).contentType(ContentType.JSON)
                            .when().body(reqBody.toString()).put("/{pp1}/{pp2}");  //toString ile Json objesini Java objesine cevirmek zorundayiz.
                                                                                // Map'lerde buna gerek kalmayacak, cunku Map zaten Java objesidir.


        //4-Assertion

        JsonPath respJP = response.jsonPath();

        Assert.assertEquals(expData.get("userId"), respJP.get("userId"));
        Assert.assertEquals(expData.get("id"), respJP.get("id"));
        Assert.assertEquals(expData.get("title"), respJP.get("title"));
        Assert.assertEquals(expData.get("body"), respJP.get("body"));

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(testDataJsonPlaceHolder.contentType, response.getContentType());
        Assert.assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri, response.getHeader("Connection"));
    }



}
