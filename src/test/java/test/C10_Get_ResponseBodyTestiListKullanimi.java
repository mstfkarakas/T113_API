package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C10_Get_ResponseBodyTestiListKullanimi {

    /*
        http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
        donen Response'in
        status code'unun 200,
        ve content type'inin application/json,
        ve response body'sindeki
            employees sayisinin 24
            ve employee'lerden birinin "Ashton Cox"
            ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu test edin
        test edin.
 */


    // 3P: Post Put Patch olunca reqBody gonderiyoruz, yoksa zorunlu degil. O zaman Json obje olustururuz ve body hazirlariz. Bu soruda yok; GET kullaniyoruz.

    @Test
    public void get01() {
        // 1-Url hazırla
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2-Expected data hazırla. (Bu soruda expected bir list yok)
        // 3-Response i kaydet

        Response response = given().when().get(url);  //reqBody olmadigi icin content type gonderme gibi pre-condition'im yok.
        response.prettyPrint();

        // 4- Assertion
        response
                .then()
                    .assertThat()
                        .statusCode(200)
                        .contentType("application/json")
                        .body("data.id", Matchers.hasSize(24),       //employees sayisinin 24 oldugunu test edin
                              "data.employee_name", Matchers.hasItem("Ashton Cox"), // employee'lerden birinin "Ashton Cox" test edin
                                "data.employee_age",Matchers.hasItems(30,40,61)   // girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu test edin
                        );
    }
}