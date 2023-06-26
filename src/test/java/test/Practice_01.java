package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.log.LogRepository;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Practice_01 {


    String token;


    @Test
    public void get01() {
        RequestSpecification specHealLife = new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com/").build();

        String token = "CpybOS25w4Y2VySdRSCkyO8AnBygyg";
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
    public void get02(){ // submitting an invalid token
        RequestSpecification specHealLife = new RequestSpecBuilder().setBaseUri("https://www.heallifehospital.com/").build();

        String token = "87654345678909876"; // fake token is entered to see the test fail.

        specHealLife.pathParams("pp1","api","pp2","opdList");

        Response response = given().spec(specHealLife).contentType(ContentType.JSON)
                            .headers("Authorization", "Bearer " + token, "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                            .when().get("/{pp1}/{pp2}");


    }

}
