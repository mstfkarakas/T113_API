package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo; // Surekli Matchers.equalTo kullanmamak icin. .* yazarsak, tum metodlar icin gecerli olur ama bilgisayara yuk olur.
// import static org.hamcrest.Matchers.*; Butun metodlari kolayca kullaniriz ama bilgisayari yorar.
public class C07_Get_BodyTekrarlardanKurtulma {

         /*
                https://restful-booker.herokuapp.com/booking/14018 url’ine
                bir GET request gonderdigimizde donen Response’un,
                status code’unun 200,
                ve content type’inin application-json,
                ve response body’sindeki
                    "firstname“in,"Jim",
                    ve "lastname“in, "Wilson",
                    ve "totalprice“in, 609,
                    ve "depositpaid“in,false,
                    ve "additionalneeds“in,"Breakfast"
                oldugunu test edin
                {
         */

    @Test
    public void get01(){
        // 1 - Gonderecegimiz Request icin gerekli olan URL/Endpoint ve ihtiyacimiz varsa Request Body hazirla
        String url = "https://restful-booker.herokuapp.com/booking/30";

        // 2 - Eger soruda bize verilmisse Expected Data hazirla. (Bu soruda yok)
        // 3 - Bize donen Response'i Actual Data olarak kaydet

        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Assertion

    /*    response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", Matchers.equalTo("sofia"),
                      "lastname", Matchers.equalTo("Brown"),
                      "totalprice", Matchers.equalTo(111),
                      "depositpaid", Matchers.equalTo(true),
                      "additionalneeds", Matchers.equalTo("Breakfast"));

    */
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("sofia"),
                        "lastname", equalTo("Brown"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "additionalneeds", equalTo("Breakfast"));

    }



}
