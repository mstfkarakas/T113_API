package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {

        /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
      */

    @Test
    public void put01(){

        // 1 - Gonderecegimiz Request icin gerekli olan URL ve ihtiyacimiz varsa Request Body hazirla
        String url = "https://jsonplaceholder.typicode.com/posts/77";

        JSONObject reqBody = new JSONObject();
        reqBody.put("title", "Ahmet");
        reqBody.put("body", "Merhaba");
        reqBody.put("userId", 10);
        reqBody.put("id", 77);

        // 2 - Eger soruda bize verilmisse Expected Data hazirla. (Bu soruda yok)
        // 3 - Bize donen Response'i Actual Data olarak kaydet

        Response response = given() // given()'dan hemen sonra pre-conditions'imizi cagiriyoruz; content type gibi
                                    .contentType(ContentType.JSON) // content type'e belirtmek zorundayiz, cunku body gondericez.
                            .when() // when()'den sonra API'ya gonderme yapariz.
                                    .body(reqBody.toString()) // Json objesi reqBody yi String'e cevirip body'ye gonderiyoruz.
                                    .put(url); // put ile url'e gonderdik
        response.prettyPrint();

        // 4 - Expected Data ile Actual Datayi karsilastirmamiz yani Assertion yapmamiz gerek

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");


    }
}
