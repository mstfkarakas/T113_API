package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.log.LogRepository;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Practice_01 {


    String token;

    @Test
    public void get01() {
        RequestSpecification specHealLife = new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com/").build();

    //    String token = "CpybOS25w4Y2VySdRSCkyO8AnBygyg";  // Manuel olarak token giriyorduk. Asagida ise metod ile cagiriyoruz.

        token = createToken(); // Asagida olusturdugumuz metod ile token cagiriyoruz.

        specHealLife.pathParams("pp1","api","pp2","opdList");

        Response response = given().spec(specHealLife).contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token, "Content-Type", ContentType.JSON, "Accept", ContentType.JSON
                )
                .when()
                .get("/{pp1}/{pp2}");

        response.prettyPrint();

        // Assertion
        response.then().assertThat().statusCode(200).body("message", Matchers.equalTo("Success"));

    }

    @Test
    public void get02(){ // submitting an invalid token to see exception error 403
        RequestSpecification specHealLife = new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com/").build();

        String token = "87654345678909876"; // fake/invalid token is entered

        specHealLife.pathParams("pp1","api","pp2","opdList");

        String exceptionMessage = "";
        try {
            Response response = given().spec(specHealLife).contentType(ContentType.JSON)
                                .headers("Authorization", "Bearer " + token, "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                                .when().get("/{pp1}/{pp2}");
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        System.out.println("exceptionMessage = " + exceptionMessage);

    //    Assert.assertTrue(exceptionMessage.contains("status code: 403"));


    }

    public String createToken(){
        /*
            "email": "hatice.kubra.ceylan.admin@heallifehospital.com",
            "password": "heallife123"
         */
        RequestSpecification specHealLife = new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com/").build();
        specHealLife.pathParams("pp1","api","pp2","getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", "hatice.kubra.ceylan.admin@heallifehospital.com");
        reqBody.put("password", "heallife123");

        Response response = given().spec(specHealLife).contentType(ContentType.JSON).when().body(reqBody.toString()).post("/{pp1}/{pp2}");
        response.prettyPrint();

        JsonPath responseJP = response.jsonPath(); // response/i jsonPath'e cevirerek icinden token'i alabiliriz.

        token = responseJP.getString("token");

        System.out.println("token = " + token);


        return token;
    }

}
