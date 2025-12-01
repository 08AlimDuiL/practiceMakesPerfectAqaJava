import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Auth {


    @Test
    public void testAuth() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Map<String, String> authData = new LinkedHashMap<>();
        authData.put("username", "admin");
        authData.put("password", "password123");

        Gson gson = new Gson();
        String body = gson.toJson(authData);

        System.out.println("Отправляемый JSON: " + body);

        // @formatter:off
        given()
                .header("Content-Type", "application/json")
                .body(body)
        .when()
                .post("/auth")
        .then()
                .statusCode(200)
                .body("token", notNullValue());
        // @formatter:on

    }

}
