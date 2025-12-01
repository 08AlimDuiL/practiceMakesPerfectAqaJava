package restfulbooker.models.response;

import lombok.Data;

import java.util.List;

@Data
public class BookingIdsResponse {

    private List<BookingId> bookings;

    @Data
    public static class BookingId {

        private int bookingid;
    }
}