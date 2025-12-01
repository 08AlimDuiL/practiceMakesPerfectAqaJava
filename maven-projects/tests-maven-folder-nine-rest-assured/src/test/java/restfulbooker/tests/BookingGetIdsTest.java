package restfulbooker.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restfulbooker.api.BookingClient;
import restfulbooker.models.response.BookingIdsResponse;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class BookingGetIdsTest extends BaseTest {

    private BookingClient bookingClient;
    private List<Integer> allBookingIds;

    @BeforeClass
    public void setup() {
        bookingClient = new BookingClient();
        allBookingIds = bookingClient.getBookingIds()
                .jsonPath()
                .getList("bookingid", Integer.class);
    }

    @Test
    public void testGetAllBookingIds() {
        Response response = bookingClient.getBookingIds();

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("$", instanceOf(List.class))
                .body("size()", greaterThan(0))
                .body("[0].bookingid", notNullValue())
                .time(lessThan(5000L));

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Integer>> bookings = jsonPath.getList("$");

        assertFalse(bookings.isEmpty());

        for (Map<String, Integer> booking : bookings) {
            assertTrue(booking.containsKey("bookingid"));
            assertNotNull(booking.get("bookingid"));
            assertTrue(booking.get("bookingid") > 0);
        }
    }
}
