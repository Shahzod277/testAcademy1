package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.Role;
import uz.jurayev.academy.domain.User;
import uz.jurayev.academy.domain.UserProfile;
import uz.jurayev.academy.exception.UserErrorMessage;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.RoleRepository;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.rest.response.UserResponse;
import uz.jurayev.academy.service.UserService;
import uz.jurayev.academy.util.responsemapper.UserResponseMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserResponseMapper userResponseMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserResponse create(UserRequest requestDto) {

        User user = new User();
        dtoToEntity(requestDto, user);
        userRepository.save(user);
        return userResponseMapper.mapFrom(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseDtos = new ArrayList<>();
        users.forEach(u -> {
            UserResponse userResponseDto = userResponseMapper.mapFrom(u);
            userResponseDtos.add(userResponseDto);
        });
        return userResponseDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        User user = Optional.of(userRepository.findById(id)).get()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return userResponseMapper.mapFrom(user);
    }

    @Override
    @Transactional
    public Result edit(Long id, UserRequest requestDto) {

        return updateUser(id, requestDto);
    }

    public Result updateUser(Long id, UserRequest requestDto) {
        User user = userRepository.findById(id).orElse(new User());
        //   user.getRoles().clear();
        user.setModifiedDate(LocalDateTime.now());
        //      dtoToEntity(requestDto, user);
        UserProfile userProfile = new UserProfile();
        userProfile.setBirthDate(requestDto.getProfile().getBirthDate());
        userProfile.setLastname(requestDto.getProfile().getLastname());
        userProfile.setFatherName(requestDto.getProfile().getFatherName());
        userProfile.setFirstname(requestDto.getProfile().getFirstname());
        userProfile.setGender(requestDto.getProfile().getGender());
        userProfile.setPassportData(requestDto.getProfile().getPassportData());
        Optional<User> byUsername = userRepository.findByUsername(requestDto.getUsername());
        if (byUsername.isPresent()) {
            if (!Objects.equals(byUsername.get().getId(), id)) {
                return new Result(UserErrorMessage.USERNAME_IS_EXISTS, false);
            } else {
                user.setUsername(byUsername.get().getUsername());
            }
        }
        user.setUsername(requestDto.getUsername());

        Optional<User> phoneNumber = userRepository.findByUserProfilePhoneNumber(requestDto.getProfile().getPhoneNumber());
        if (phoneNumber.isPresent()) {
            if (!Objects.equals(phoneNumber.get().getId(), id)) {
                return new Result(UserErrorMessage.PHONE_NUMBER_IS_EXISTS, false);
            }
            userProfile.setPhoneNumber(phoneNumber.get().getUserProfile().getPhoneNumber());

        }
        userProfile.setPhoneNumber(requestDto.getProfile().getPhoneNumber());
        Optional<User> byEmail = userRepository.findByEmail(requestDto.getEmail());
        if (byEmail.isPresent()) {
            if (!Objects.equals(byEmail.get().getId(), id)) {
                return new Result(UserErrorMessage.EMAIL_IS_EXISTS, false);
            }
            user.setEmail(byEmail.get().getEmail());

        }
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setUserProfile(userProfile);
        userRepository.save(user);
        return new Result("User successfully updated", true);
    }

    @Override
    @Transactional
    public Result delete(Long id) {

        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            userById.get().removeRoles();
            userRepository.deleteById(userById.get().getId());
            return new Result("User successfully deleted", true);
        }
        return new Result("User not found", false);
    }

    @Transactional
    public void dtoToEntity(UserRequest request, User user) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User email is already exists!");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User username is already exists");
        }
        if (userRepository.existsByUserProfile_PhoneNumber(request.getProfile().getPhoneNumber())) {
            throw new RuntimeException("User phone number is already exists");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserProfile(UserProfile.builder()
                .firstname(request.getProfile().getFirstname())
                .lastname(request.getProfile().getLastname())
                .birthDate(request.getProfile().getBirthDate())
                .phoneNumber(request.getProfile().getPhoneNumber())
                .passportData(request.getProfile().getPassportData())
                .gender(request.getProfile().getGender())
                .build()
        );

        request.getRoles().forEach(roleName -> {
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
            roleRepository.save(role);
        });
    }
}
