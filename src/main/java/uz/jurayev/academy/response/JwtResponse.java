package uz.jurayev.academy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jurayev.academy.security.SecurityConstant;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String refreshToken;
    private String type = "Bearer";
    private Long userId;
   private Long token_expire= SecurityConstant.EXPIRE_AT;
    private Long refresh_token_expire=SecurityConstant.REFRESH_TOKEN_EXPIRE_AT;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String refreshToken, Long userId, String username, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
