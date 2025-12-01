package restfulbooker.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response getBookingIds() {
        return given()
                .when()
                .get("/booking");
    }

    public Response getBookingIdsWithQueryParams(String firstname,
                                                 String lastname,
                                                 String checkin,
                                                 String checkout) {
        RequestSpecification request = given();

        if (firstname != null) request.queryParam("firstname", firstname);
        if (lastname != null) request.queryParam("lastname", lastname);
        if (checkin != null) request.queryParam("checkin", checkin);
        if (checkout != null) request.queryParam("checkout", checkout);

        return request.when().get("/booking");
    }

    public Response getBookingById(int id) {
        return given()
                .when()
                .get("/booking/" + id);
    }
}