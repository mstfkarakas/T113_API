package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseURL {
    protected RequestSpecification specHerokuApp;

    @Before
    public void setup(){
        specHerokuApp = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}