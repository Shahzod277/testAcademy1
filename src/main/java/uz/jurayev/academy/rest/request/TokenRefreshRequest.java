package uz.jurayev.academy.rest.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {

    @NotBlank
    String refreshToken;
}
