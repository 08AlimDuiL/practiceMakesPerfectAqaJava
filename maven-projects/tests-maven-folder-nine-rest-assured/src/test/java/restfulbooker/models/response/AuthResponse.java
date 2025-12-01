package restfulbooker.models.response;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
//
//    public AuthResponse(String token) {
//
//        this.token = token;
//    }
//
//    public String getToken() {
//
//        return token;
//    }
}
