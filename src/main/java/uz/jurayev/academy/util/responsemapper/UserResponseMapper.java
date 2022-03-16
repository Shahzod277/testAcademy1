package uz.jurayev.academy.util.responsemapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Role;
import uz.jurayev.academy.domain.User;
import uz.jurayev.academy.rest.response.RoleResponse;
import uz.jurayev.academy.rest.response.UserResponse;
import uz.jurayev.academy.util.Mapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResponseMapper implements Mapper<User, UserResponse> {

    private final RoleResponseMapper roleResponseMapper;
    private final UserProfileResponseMapper profileResponseMapper;

    @Override
    public UserResponse mapFrom(User user) {

        List<Role> roles = user.getRoles();
        List<RoleResponse> roleResponseDtos = new ArrayList<>();
        roles.forEach(r -> {
            RoleResponse roleResponseDto = roleResponseMapper.mapFrom(r);
            roleResponseDtos.add(roleResponseDto);
        });

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roleResponseDtos)
                .userProfile(profileResponseMapper.mapFrom(user.getUserProfile()))
                .build();
    }
}
