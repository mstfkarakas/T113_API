package test;

import baseURL.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPOJO;
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
                        "lastname" : “Bak",
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
        specHerokuApp.pathParam("pp1","booking");

        BookingDatesPOJO bookingDatesPOJO = new BookingDatesPOJO("2021-06-01", "2021-06-10");
        BookingPOJO reqBody = new BookingPOJO("Ali", "Bak", 500, false, bookingDatesPOJO, "wi-fi");
        BookingExpBodyPOJO expData = new BookingExpBodyPOJO(24, reqBody);

        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");
        response.prettyPrint();

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
