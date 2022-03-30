package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.*;
import uz.jurayev.academy.exception.ErrorMessages;
import uz.jurayev.academy.exception.TutorNotFoundException;
import uz.jurayev.academy.exception.UserErrorMessage;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.GroupRepository;
import uz.jurayev.academy.repository.StudentRepository;
import uz.jurayev.academy.repository.TutorRepository;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.rest.request.AdminTutorRequest;
import uz.jurayev.academy.rest.response.AdminTutorResponse;
import uz.jurayev.academy.rest.response.UserResponse;
import uz.jurayev.academy.service.AdminTutorService;
import uz.jurayev.academy.util.requestmapper.AddressRequestMapper;
import uz.jurayev.academy.util.requestmapper.UserRequestMapper;
import uz.jurayev.academy.util.responsemapper.AdminTutorResponseMapper;
import uz.jurayev.academy.util.responsemapper.StudentResponseMapper;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminTutorServiceImpl implements AdminTutorService {

    private final TutorRepository tutorRepository;
    private final GroupRepository groupRepository;
    private final AdminTutorResponseMapper tutorResponseMapper;
    private final UserRequestMapper userRequestMapper;
    private final AddressRequestMapper addressRequestMapper;
    private final StudentResponseMapper studentResponseMapper;
    private final StudentRepository studentRepository;
    private final AdminUserServiceImpl adminUserService;
    private final UserRepository userRepository;

    @Transactional
    public Result createTutor(AdminTutorRequest tutorDto) {
        Tutor tutor = new Tutor();
        requestToEntity(tutorDto, tutor);
    //    UserResponse userResponse = adminUserService.create(tutorDto.getUser());
        Result result = userRequestMapper.mapFrom(tutorDto.getUser());
        tutor.setUser((User) result.getObject());
        tutorRepository.save(tutor);
        return new Result("Tutor successfully saved", true);

    }

    @Transactional
    public Result removeTutor(Integer id) {
        tutorRepository.deleteById(id);
        return new Result("Tutor successfully deleted", true);
    }

    @Transactional
    public List<AdminTutorResponse> getAllTutor(Principal principal) {

        List<Tutor> tutors = tutorRepository.getAllTutorByUser(principal.getName());
        List<AdminTutorResponse> dtos = new ArrayList<>();
        tutors.forEach(tutor -> {
            AdminTutorResponse tutorResponseDto = tutorResponseMapper.mapFrom(tutor);
            dtos.add(tutorResponseDto);
        });
        return dtos;
    }

    @Transactional
    public AdminTutorResponse getTutorById(Integer id) {
        Tutor tutor = Optional.of(tutorRepository.findById(id)).get().orElseThrow(() ->
                new TutorNotFoundException(ErrorMessages.TUTOR_NOT_FOUND.getMessage()));
        return tutorResponseMapper.mapFrom(tutor);
    }

    @Transactional
    public Result updateTutor(Integer id, AdminTutorRequest tutorDto) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() ->
                new TutorNotFoundException(ErrorMessages.TUTOR_NOT_FOUND.getMessage()));
        tutor.getStudentGroups().clear();
        requestToEntity(tutorDto, tutor);
        Result result = updateUser(tutor, tutorDto.getUser());
        tutor.setUser((User)result.getObject());
        tutorRepository.save(tutor);

        return new Result("Successfully updated", true);
    }

    @Transactional
    public List<StudentResponse> getAllStudent() {
        List<Student> all = studentRepository.findAll();
        List<StudentResponse> list = new ArrayList<>();
        all.forEach(student -> {
            StudentResponse studentResponseDto = studentResponseMapper.mapFrom(student);
            list.add(studentResponseDto);
        });
        return list;
    }

    @Transactional
    public void requestToEntity(AdminTutorRequest tutorDto, Tutor tutor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            tutor.setAvtor(currentUserName);
            tutor.setAddress(addressRequestMapper.mapFrom(tutorDto.getAddress()));
            tutor.setCategory(tutorDto.getCategory());
            tutor.setLevel(tutorDto.getLevel());
            tutor.setDescription(tutorDto.getDescription());
            tutor.setModifiedDate(LocalDateTime.now());
            tutorDto.getGroups().forEach(groupName -> {
                StudentGroup studentGroup;
                Optional<StudentGroup> groupByGroupName = groupRepository.findByGroupName(groupName);
                if (groupByGroupName.isPresent()) {
                    studentGroup = groupByGroupName.get();
                    studentGroup.setGroupName(studentGroup.getGroupName());
                    tutor.addGroup(studentGroup);
                } else {
                    studentGroup = new StudentGroup();
                    studentGroup.setGroupName(groupName);
                    studentGroup.setTutor(new Tutor());
                    tutor.addGroup(studentGroup);
                }
            });


        }
    }
    @Transactional
    public Result updateUser(Tutor tutor, UserRequest requestDto) {
        //   user.getRoles().clear();
        User user = tutor.getUser();
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
            if (!Objects.equals(byUsername.get().getId(), user.getId())) {
                return new Result(UserErrorMessage.USERNAME_IS_EXISTS, false);
            } else {
                user.setUsername(byUsername.get().getUsername());
            }
        }
        user.setUsername(requestDto.getUsername());

        Optional<User> byPhoneNumber = userRepository.findByUserProfilePhoneNumber(requestDto.getProfile().getPhoneNumber());
        if (byPhoneNumber.isPresent()) {
            if (!Objects.equals(byPhoneNumber.get().getId(),user.getId())) {
                return new Result(UserErrorMessage.PHONE_NUMBER_IS_EXISTS, false);
            }
            userProfile.setPhoneNumber(byPhoneNumber.get().getUserProfile().getPhoneNumber());

        }
        userProfile.setPhoneNumber(requestDto.getProfile().getPhoneNumber());
        Optional<User> byEmail = userRepository.findByEmail(requestDto.getEmail());
        if (byEmail.isPresent()) {
            if (!Objects.equals(byEmail.get().getId(),user.getId())) {
                return new Result(UserErrorMessage.EMAIL_IS_EXISTS, false);
            }
            user.setEmail(byEmail.get().getEmail());
        }
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setUserProfile(userProfile);
        return new Result("User successfully updated", true,user);
    }
}
