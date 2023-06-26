package test;

import baseURL.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyDataPOJO;
import pojos.DummyExpBodyPOJO;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Get_Pojo extends DummyBaseUrl {

         /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
	Response Body
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get(){

        specDummy.pathParams("pp1", "api","pp2", "v1", "pp3", "employee", "pp4", 3);

        DummyDataPOJO data = new DummyDataPOJO(3, "Ashton Cox", 86000, 66, "");
        DummyExpBodyPOJO expBody = new DummyExpBodyPOJO("success", data, "Successfully! Record has been fetched.");

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");
        response.prettyPrint();

        DummyExpBodyPOJO respPojo = response.as(DummyExpBodyPOJO.class);

        assertEquals(expBody.getData().getId(), respPojo.getData().getId());
        assertEquals(expBody.getData().getEmployee_name(), respPojo.getData().getEmployee_name());
        assertEquals(expBody.getData().getEmployee_salary(), respPojo.getData().getEmployee_salary());
        assertEquals(expBody.getData().getEmployee_age(), respPojo.getData().getEmployee_age());
        assertEquals(expBody.getData().getProfile_image(), respPojo.getData().getProfile_image());

        assertEquals(expBody.getStatus(), respPojo.getStatus());
        assertEquals(expBody.getMessage(), respPojo.getMessage());

    }


}
