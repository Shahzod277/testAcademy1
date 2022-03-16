package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class UserResponse {

    Long id;
    String username;
    String email;
    List<RoleResponse> roles;
    UserProfileResponse userProfile;

}
