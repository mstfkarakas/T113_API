package test;

import baseURL.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingdatesPOJO;
import pojos.BookingExpBodyPOJO;
import pojos.BookingPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Post_Pojo extends HerokuAppBaseURL {

/*  https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
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
                        Response Body = Expected Data
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
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }    */

    @Test
    public void post01(){
        // 1) and 2) URL and Body and Exp Body
        specHerokuApp.pathParam("pp1","booking");

        BookingdatesPOJO bookingdates = new BookingdatesPOJO("2021-06-01", "2021-06-10");
        BookingPOJO reqBody = new BookingPOJO("Ali", "Bak", 500, false, bookingdates, "wi-fi");
        BookingExpBodyPOJO expData = new BookingExpBodyPOJO(24, reqBody);
        // 3) Response'i kaydet
        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");
        response.prettyPrint();
        // 4) Assertion. Sadece metodlari kullanarak kod yazmaya FUNCTIONAL PROGRAMMING denir, burda oyle yaptik.
        BookingExpBodyPOJO respPOJO = response.as(BookingExpBodyPOJO.class);

        assertEquals(expData.getBooking().getFirstname(), respPOJO.getBooking().getFirstname());
        assertEquals(expData.getBooking().getLastname(), respPOJO.getBooking().getLastname());
        assertEquals(expData.getBooking().getTotalprice(), respPOJO.getBooking().getTotalprice());
        assertEquals(expData.getBooking().getDepositpaid(), respPOJO.getBooking().getDepositpaid());
        assertEquals(expData.getBooking().getBookingdates().getCheckin(), respPOJO.getBooking().getBookingdates().getCheckin());
        assertEquals(expData.getBooking().getBookingdates().getCheckout(), respPOJO.getBooking().getBookingdates().getCheckout());

        assertEquals(expData.getBooking().getAdditionalneeds(), respPOJO.getBooking().getAdditionalneeds());
    }
}
