package restfulbooker.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

//    public AuthRequest(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getUsername() {
//
//        return username;
//    }
//
//    public String getPassword() {
//
//        return password;
//    }
}
