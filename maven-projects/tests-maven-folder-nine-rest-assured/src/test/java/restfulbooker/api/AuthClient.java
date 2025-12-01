package restfulbooker.api;

import restfulbooker.models.request.AuthRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response authenticate(AuthRequest authRequest) {

        return given()
                .header("Content-Type", "application/json")
                .body(authRequest)
                .post("/auth");
    }

    public String getToken(AuthRequest authRequest) {

        return authenticate(authRequest)
                .then()
                .extract()
                .path("token");
    }
}