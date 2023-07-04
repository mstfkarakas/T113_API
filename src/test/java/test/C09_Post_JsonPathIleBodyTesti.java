package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C09_Post_JsonPathIleBodyTesti {

        /*
            https://restful-booker.herokuapp.com/booking
             url’ine asagidaki body'ye sahip
            bir POST request gonderdigimizde
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
            donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve response body’sindeki
                "firstname“in,"Ali",
                ve "lastname“in, "Bak",
                ve "totalprice“in,500,
                ve "depositpaid“in,false,
                ve "checkin" tarihinin 2021-06-01
                ve "checkout" tarihinin 2021-06-10
                ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
     */

    //JSON Path sadece Response ile calisir.
    @Test
    public void post01(){
        // 1 - Gonderecegimiz Request icin gerekli olan URL/Endpoint ve ihtiyacimiz varsa Request Body hazirla

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject bookingDates = new JSONObject(); //obje olustururken ICTEN baslayip DISA dogru gidiyoruz.
        bookingDates.put("checkin", "2021-06-01");
        bookingDates.put("checkout", "2021-06-10");

        JSONObject reqBody = new JSONObject(); // Distaki JSON objemiz.

        reqBody.put("firstname",  "Ali");
        reqBody.put("lastname",  "Bak");
        reqBody.put("totalprice",  500);
        reqBody.put("depositpaid",  false);
        reqBody.put("bookingdates",  bookingDates);
        reqBody.put("additionalneeds",  "wi-fi");


        // 2 - Eger soruda bize verilmisse Expected Data hazirla. (Bu soruda yok)
        // 3 - Bize donen Response'i Actual Data olarak kaydet

        Response response = given().contentType(ContentType.JSON)
                            .when()
                                    .body(reqBody.toString())
                                    .post(url);

        response.prettyPrint();

        // 4- Assertion

        response
                .then()
                    .assertThat()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .body("booking.firstname", equalTo("Ali"), // Matcher.equalTo yerine sadece equalTo yazip import class yapiyoruz. Kisa yol.
                                "booking.lastname", equalTo("Bak"),
                                "booking.totalprice", equalTo(500),
                                "booking.depositpaid", equalTo(false),
                                "booking.additionalneeds", equalTo("wi-fi"),
                                "booking.bookingdates.checkin", equalTo("2021-06-01"),
                                "booking.bookingdates.checkout", equalTo("2021-06-10")
                                );

    }
}
