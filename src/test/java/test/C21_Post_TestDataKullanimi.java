package test;

import baseURL.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerokuAppBaseURL {

    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.
    Request body
          {
          "firstname" : "Ali",
          "lastname" : "Bak",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
    Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void post01() { // her inner json object icin bir metod olusturmak lazim. testData icinde.

        // 1) URL ve request body hazirla
        specHerokuApp.pathParam("pp1", "booking");

        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();
        JSONObject reqBody = testDataHerokuapp.bookingOlusturJSON();

        // 2) Expected data olustur
        JSONObject expData = testDataHerokuapp.expectedBodyOlusturJSON();

        // 3) Response'u kaydet
        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().body(reqBody.toString()).post("/{pp1}"); //toString ile Json objesini Java objesine cevirmek zorundayiz.
                                                                // Map'lerde buna gerek kalmayacak, cunku Map zaten Java objesidir.

        // 4) Assertion
        JsonPath respJP = response.jsonPath();

        assertEquals(testDataHerokuapp.successfulStatusCode, response.getStatusCode());
        //              expected data               VS                               actual data
        assertEquals(expData.getJSONObject("booking").get("firstname"), respJP.get("booking.firstname"));
        assertEquals(expData.getJSONObject("booking").get("lastname"), respJP.get("booking.lastname"));
        assertEquals(expData.getJSONObject("booking").get("totalprice"), respJP.get("booking.totalprice"));
        assertEquals(expData.getJSONObject("booking").get("depositpaid"), respJP.get("booking.depositpaid"));
        assertEquals(expData.getJSONObject("booking").get("additionalneeds"), respJP.get("booking.additionalneeds"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), respJP.get("booking.bookingdates.checkin"));
        assertEquals(expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), respJP.get("booking.bookingdates.checkout"));
    }
}
