package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleResponse {

    Integer id;
    String roleName;
}
