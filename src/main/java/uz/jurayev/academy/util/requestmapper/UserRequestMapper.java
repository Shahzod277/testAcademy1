package uz.jurayev.academy.util.requestmapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Role;
import uz.jurayev.academy.domain.User;
import uz.jurayev.academy.domain.UserProfile;
import uz.jurayev.academy.repository.RoleRepository;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.util.Mapper;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRequestMapper implements Mapper<UserRequest, User> {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User mapFrom(UserRequest userRequestDto) {

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setUserProfile(UserProfile.builder()
                .firstname(userRequestDto.getProfile().getFirstname())
                .lastname(userRequestDto.getProfile().getLastname())
                .birthDate(userRequestDto.getProfile().getBirthDate())
                .phoneNumber(userRequestDto.getProfile().getPhoneNumber())
                .passportData(userRequestDto.getProfile().getPassportData())
                .gender(userRequestDto.getProfile().getGender())
                .build()
        );
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }
        userRequestDto.getRoles().forEach(roleName -> {
            Role role;
            Optional<Role> roleById = roleRepository.findByRoleName("ROLE_" + roleName.toUpperCase());
            if (roleById.isPresent()) {
                role = roleById.get();
                role.setRoleName("ROLE_" + roleName.toUpperCase());
                user.addRole(role);
            } else {
                role = new Role();
                role.setRoleName("ROLE_" + roleName.toUpperCase());
                role.setUsers(new ArrayList<>());
                user.addRole(role);
            }
//            roleRepository.save(role);
        });
        return user;
    }
}
