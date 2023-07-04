package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {

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
        // 1) url hazırla
        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2) Expected olustur
        JSONObject data = new JSONObject();  // Once ICERDEN basliyoruz.
        data.put("id",3);
        data.put("employee_name", "Ashton Cox");
        data.put("employee_salary", 86000);
        data.put("employee_age", 66);
        data.put("profile_image", "");

        JSONObject expectedData = new JSONObject();  // Simdi DISARDAKIni olusturuyoruz.
        expectedData.put("status", "success");
        expectedData.put("data", data);
        expectedData.put("message", "Successfully! Record has been fetched.");

        // 3) Response kaydet
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4) Assertion
        SoftAssert softAssert = new SoftAssert();
        JsonPath respJP = response.jsonPath();

        softAssert.assertEquals(respJP.get("status"), expectedData.get("status"));
        softAssert.assertEquals(respJP.get("message"), expectedData.get("message"));
        softAssert.assertEquals(respJP.get("data.id"), expectedData.getJSONObject("data").get("id"));
        softAssert.assertEquals(respJP.get("data.employee_name"), expectedData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(respJP.get("data.employee_salary"), expectedData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(respJP.get("data.employee_age"), expectedData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(respJP.get("data.profile_image"), expectedData.getJSONObject("data").get("profile_image"));
        softAssert.assertAll();
    }


}
