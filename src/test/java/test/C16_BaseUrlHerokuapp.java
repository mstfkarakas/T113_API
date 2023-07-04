package test;

import baseURL.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuAppBaseURL {
     /*
        Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    */


    @Test
    public void get01(){
        /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve Response’ta 1789 bookingid'sine sahip bir booking oldugunu test edin
    */
        //1- url hazırla
        specHerokuApp.pathParams("pp1","booking");

        //2- exp data hazırla
        //3-response kaydet
        Response response=given().spec(specHerokuApp).when().get("/{pp1}");
        response.prettyPrint();

        //4-Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid", Matchers.hasItem(1789));
    }
    @Test
    public void post01(){
          /*
        2- https://restful-booker.herokuapp.com/booking endpointine asagidaki
         body’ye sahip bir POST request gonderdigimizde donen response’un status
         code’unun 200 oldugunu ve “firstname” degerinin “Ali” oldugunu test edin
      {
      "firstname" : "Ali",
      "lastname" : “Bak",
      "totalprice" : 500,
      "depositpaid" : false,
      "bookingdates" : {
                    "checkin" : "2021-06-01",
                    "checkout" : "2021-06-10"
                    },
      "additionalneeds" : "wi-fi"
       }
      */

        //1-url hazırla
        specHerokuApp.pathParams("pp1","booking ");

        //2- exp data hazırla
        //3-response kaydet
        JSONObject bookingdates=new JSONObject();
        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        JSONObject reqBody=new JSONObject();
        reqBody.put("firstname" , "Ali");
        reqBody.put("lastname" , "Bak");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put("bookingdates" , bookingdates);
        reqBody.put("additionalneeds" ,"wi-fi");

        Response response=given().
                spec(specHerokuApp).
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post("/{pp1}");
        response.prettyPrint();

        //4-Assertion
        response.
                then().
                assertThat().statusCode(200).
                body("firstname",Matchers.equalTo("Ali"));

    }
}