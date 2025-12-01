package restfulbooker.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import restfulbooker.models.request.AuthRequest;
import restfulbooker.utils.TestConfig;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static String authToken;

    @BeforeClass
    public void baseSetup() {
        setupRestAssured();
        initAuthToken();
    }

    private void setupRestAssured() {
        RestAssured.baseURI = TestConfig.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    private void initAuthToken() {
        if (authToken == null) {
            try {
                AuthRequest authRequest = new AuthRequest(
                        TestConfig.getUsername(),
                        TestConfig.getPassword()
                );

                authToken = given()
                        .body(authRequest)
                        .post("/auth")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("token");

            } catch (Exception e) {
                System.err.println("Не удалось получить токен: " + e.getMessage());
                authToken = "";
            }
        }
    }

    protected String getFreshToken() {
        AuthRequest authRequest = new AuthRequest(
                TestConfig.getUsername(),
                TestConfig.getPassword()
        );

        return given()
                .body(authRequest)
                .post("/auth")
                .then()
                .extract()
                .path("token");
    }

    protected boolean isTokenValid() {

        return authToken != null && !authToken.isEmpty();
    }
}