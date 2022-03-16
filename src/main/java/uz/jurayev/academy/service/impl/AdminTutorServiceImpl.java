package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.exception.ErrorMessages;
import uz.jurayev.academy.exception.TutorNotFoundException;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.GroupRepository;
import uz.jurayev.academy.repository.StudentRepository;
import uz.jurayev.academy.repository.TutorRepository;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.rest.request.AdminTutorRequest;
import uz.jurayev.academy.rest.response.AdminTutorResponse;
import uz.jurayev.academy.service.AdminTutorService;
import uz.jurayev.academy.util.requestmapper.AddressRequestMapper;
import uz.jurayev.academy.util.requestmapper.UserRequestMapper;
import uz.jurayev.academy.util.responsemapper.AdminTutorResponseMapper;
import uz.jurayev.academy.util.responsemapper.StudentResponseMapper;
import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public Result createTutor(AdminTutorRequest tutorDto) {
        Tutor tutor = new Tutor();
        requestToEntity(tutorDto, tutor);
        return new Result("Tutor successfully saved", true);
    }

    @Transactional
    public Result removeTutor(Integer id) {
        tutorRepository.deleteById(id);
        return new Result("Tutor successfully deleted", true);
    }

    @Transactional
    public List<AdminTutorResponse> getAllTutor() {
        List<Tutor> tutors = tutorRepository.findAll();
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

    private void requestToEntity(AdminTutorRequest tutorDto, Tutor tutor) {
        tutor.setAddress(addressRequestMapper.mapFrom(tutorDto.getAddress()));
        tutor.setCategory(tutorDto.getCategory());
        tutor.setLevel(tutorDto.getLevel());
        tutor.setDescription(tutorDto.getDescription());
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
        tutor.setUser(userRequestMapper.mapFrom(tutorDto.getUser()));
        tutorRepository.save(tutor);
    }
}
