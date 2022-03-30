package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.Address;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.domain.classificators.CreativePotential;
import uz.jurayev.academy.domain.classificators.CreativePotentialCategory;
import uz.jurayev.academy.exception.ErrorMessages;
import uz.jurayev.academy.exception.StudentNotFoundException;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.*;
import uz.jurayev.academy.rest.GroupRequest;
import uz.jurayev.academy.rest.request.StudentRequest;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.service.StudentService;
import uz.jurayev.academy.util.requestmapper.AddressRequestMapper;
import uz.jurayev.academy.util.requestmapper.FamilyInfoRequestMapper;
import uz.jurayev.academy.util.requestmapper.StudyRequestMapper;
import uz.jurayev.academy.util.responsemapper.StudentGroupResponseMapper;
import uz.jurayev.academy.util.responsemapper.StudentResponseMapper;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorStudentServiceImpl implements StudentService {

    private final AddressRepository addressRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final StudyRequestMapper studyRequestMapper;
    private final FamilyInfoRequestMapper familyInfoRequestMapper;
    private final AddressRequestMapper addressRequestMapper;
    private final TutorServiceImpl tutorService;
    private final CreativePotentialRepository creativePotentialRepository;
    private final CreativePontentialCategoryRepository categoryRepository;
    private final StudentResponseMapper studentResponseMapper;
    private final StudentGroupResponseMapper groupResponseMapper;

    @Transactional
    @Override
    public Result save(StudentRequest request) {

        Student student = new Student();
        studentDtoToEntity(request, student);
        studentRepository.save(student);
        return new Result("Student successfull saved", true);
    }

    @Transactional
    @Override
    public Result updateStudent(StudentRequest request, Integer id) {

        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException(ErrorMessages.STUDENT_NOT_FOUND.getMessage()));
        student.setModifiedDate(LocalDateTime.now());
        student.getCategories().clear();
//        student.getCategories().forEach(e -> {
//            String value = e.getValue();
//            CreativePotentialCategory category = categoryRepository.findByValue(value).orElseThrow(
//                    () -> new CreativePotentialCategoryException(ErrorMessages.CREATIVE_POTENTIAL_CATEGORY_NOT_FOUND.getMessage()));
//            student.removeCPCategory(category);
//        });

        studentDtoToEntity(request, student);
        studentRepository.save(student);
        return new Result("Student successfully updated", true);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getStudents(Principal principal,Integer page,Integer limit) {
        Pageable pageable= PageRequest.of(page,limit);
        Tutor currentTutor = tutorService.getCurrentTutor(principal);
        Page<Student> allstudent = studentRepository.getAllStudent(currentTutor.getUser().getUsername(),pageable);
        List<Student> studentInfoDtos =allstudent.getContent();
        List<StudentResponse> studentResponses=new ArrayList<>();
        studentInfoDtos.forEach(student -> {
            StudentResponse infoDto = studentResponseMapper.mapFrom(student);
            studentResponses.add(infoDto);
        });
        return studentResponses;
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudentByGroup(String groupName) {
        List<Student> allstudent = studentRepository.findStudentByGroup_GroupName(groupName);
        List<StudentResponse> studentInfoDtos = new ArrayList<>();
        allstudent.forEach(student -> {
            StudentResponse infoDto = studentResponseMapper.mapFrom(student);
            studentInfoDtos.add(infoDto);
        });
        return studentInfoDtos;
    }


    @Transactional(readOnly = true)
    @Override
    public StudentResponse getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        return studentResponseMapper.mapFrom(student);

    }

    @Transactional
    @Override
    public Result delete(Integer id) {
        try {
            studentRepository.deleteById(id);
            return new Result("student deleted", true);
        } catch (Exception e) {
            return new Result("" + e.getMessage(), false);
        }
    }



    //    @Override   //apidan studentni olib keladi
//    public StudentInfoDto getStudentByApi(PinflDto pinflDto) {
//
//        List<ResponseToken> responseTokens = new ArrayList<>();
//        responseTokens.add(tokenRepository.getLastToken());
//        String pinfl = pinflDto.getPinfl();
//        URI baseUrl = URI.create(SecurityConstant.GET_STUDENT_URL + pinfl);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        httpHeaders.setBearerAuth(responseTokens.get(responseTokens.size() - 1).getAccess_token());
//
//        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<Data> data = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, Data.class);
//        Data body = data.getBody();
//        assert body != null;
//        return body.getStudentInfo();

    @Transactional(readOnly = true)
    public List<GroupRequest> getGroups(Principal principal) {
        Tutor currentTutor = tutorService.getCurrentTutor(principal);
        List<StudentGroup> allGroupsByUsername = groupRepository.getAllGroupsByUsername(currentTutor.getUser().getEmail());
        List<GroupRequest> groupDtos = new ArrayList<>();
        allGroupsByUsername.forEach(studentGroup -> {
            groupDtos.add(groupResponseMapper.mapFrom(studentGroup));
        });
        return groupDtos;
    }

    @Transactional()
    public List<StudentResponse> searchStudent(String word, Principal principal) {
        Tutor currentTutor = tutorService.getCurrentTutor(principal);
        String username = currentTutor.getUser().getUsername();
        List<Student> allstudent = studentRepository.searchFirstnameAndLastname(word, username);
        if (allstudent.isEmpty()) {
            return new ArrayList<>();
        }
        List<StudentResponse> studentInfoDtos = new ArrayList<>();
        allstudent.forEach(student -> {
            StudentResponse infoDto = studentResponseMapper.mapFrom(student);
            studentInfoDtos.add(infoDto);
        });
        return studentInfoDtos;

    }

    @Transactional
    public void studentDtoToEntity(StudentRequest request, Student student) {

        student.setGender(request.getGender());
        student.setFatherName(request.getFatherName());
        student.setFirstname(request.getFirstname());
        student.setLastname(request.getLastname());
        student.setBirthDate(request.getBirthDate());
        student.setStudyInfo(studyRequestMapper.mapFrom(request.getStudyInfo()));
        student.setNationality(request.getNationality());
        student.setFamilyInfo(familyInfoRequestMapper.mapFrom(request.getFamilyInformation()));
        Optional<StudentGroup> byGroupName = groupRepository.findByGroupName(request.getGroup().getGroupName());
        if (byGroupName.isPresent()) {
            student.setGroup(byGroupName.get());
        } else {
            student.setGroup(new StudentGroup());
        }
        Address address = addressRequestMapper.mapFrom(request.getAddressRequest());
        addressRepository.save(address);
        student.setAddress(address);
        student.setFamilyStatus(request.getFamilyStatus());
        student.setPassportData(request.getPassportData());

        request.getCreativePotential().forEach(creativePotentialDto -> {
            CreativePotential creativePotential = new CreativePotential();
            Optional<CreativePotential> byType = creativePotentialRepository.findByType(creativePotentialDto.getType());
            if (byType.isPresent()) {
                creativePotential = byType.get();
            } else {
                creativePotential.setType(creativePotentialDto.getType().toLowerCase());
            }
            for (String s : creativePotentialDto.getCategory()) {
                CreativePotentialCategory creativePotentialCategory = new CreativePotentialCategory();
                Optional<CreativePotentialCategory> byValue = categoryRepository.findByValue(s);
                if (byValue.isPresent()) {
                    creativePotentialCategory = byValue.get();
                } else {
                    creativePotentialCategory.setValue(s.toLowerCase());
                }
                creativePotentialRepository.save(creativePotential);
                creativePotential.addCreativePotentialCategory(creativePotentialCategory);

                //  CreativePotentialCategory category = categoryRepository.save(creativePotentialCategory);
                student.addCPCategory(creativePotentialCategory);
                // student.getCategories().add(category);
            }
        });
    }
}



