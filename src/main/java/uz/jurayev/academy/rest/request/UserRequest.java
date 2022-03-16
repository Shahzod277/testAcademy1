package uz.jurayev.academy.rest.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserRequest {

        private String username;
        private String password;
        private String email;
        private List<String> roles;
        private UserProfileRequest profile;
}
