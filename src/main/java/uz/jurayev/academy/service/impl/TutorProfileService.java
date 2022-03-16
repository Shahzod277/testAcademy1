package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.repository.GroupRepository;
import uz.jurayev.academy.repository.StudentRepository;
import uz.jurayev.academy.repository.TutorRepository;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorProfileService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TutorRepository tutorRepository;

    public List<Student> getAllStudent(Principal principal) {
        String name = principal.getName();
       // List<Student> allStudent = groupRepository.getAllStudent();
       // if (allStudent.isEmpty()) {
        //    return null;
        //}
        return null;
    }


}
