package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Role;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.response.RoleResponse;
import uz.jurayev.academy.util.Mapper;

@Component
public class RoleResponseMapper implements Mapper<Role, RoleResponse,RoleResponse> {

    @Override
    public RoleResponse mapFrom(Role role) {

       return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
