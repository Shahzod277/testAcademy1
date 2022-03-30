package uz.jurayev.academy.util.requestmapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Role;
import uz.jurayev.academy.domain.User;
import uz.jurayev.academy.domain.UserProfile;
import uz.jurayev.academy.exception.UserErrorMessage;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.RoleRepository;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.util.Mapper;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRequestMapper implements Mapper<UserRequest, User,Result> {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository  userRepository;

    @Override
    public Result mapFrom(UserRequest userRequestDto) {
        User user = new User();
        Optional<User> byUsername = userRepository.findByUsername(userRequestDto.getUsername());
        if (byUsername.isPresent()){
            return new Result(UserErrorMessage.USERNAME_IS_EXISTS,false);
        }
        user.setUsername(userRequestDto.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(userRequestDto.getEmail());
        if (byEmail.isPresent()) {
            return new Result(UserErrorMessage.EMAIL_IS_EXISTS,false);
        }
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        Optional<User> phoneNumber = userRepository.findByUserProfilePhoneNumber(userRequestDto.getProfile().getPhoneNumber());
        if (byEmail.isPresent()) {
            return new Result(UserErrorMessage.PHONE_NUMBER_IS_EXISTS,false);
        }
        user.setUserProfile(UserProfile.builder()
                .firstname(userRequestDto.getProfile().getFirstname())
                .lastname(userRequestDto.getProfile().getLastname())
                .birthDate(userRequestDto.getProfile().getBirthDate())
                        .fatherName(userRequestDto.getProfile().getFatherName())
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
        return new Result("user saved",true,user);
    }
}
