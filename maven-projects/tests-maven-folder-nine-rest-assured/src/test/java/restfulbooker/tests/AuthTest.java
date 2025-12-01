package restfulbooker.tests;

import org.testng.annotations.Test;
import restfulbooker.api.AuthClient;
import restfulbooker.models.request.AuthRequest;
import restfulbooker.utils.TestConfig;

import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    public void testAuth() {

        AuthRequest authRequest = AuthRequest.builder()
                .username(TestConfig.getUsername())
                .password(TestConfig.getPassword())
                .build();

        authClient.authenticate(authRequest)
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", matchesPattern("^[a-zA-Z0-9]+$"))
                .time((lessThan(5000L)));
    }

    @Test
    public void testAuthenticationWithEmptyPassword() {

        AuthRequest authRequest = AuthRequest.builder()
                .username(TestConfig.getUsername())
                .password("")
                .build();


        authClient.authenticate(authRequest)
                .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }

}
