package uz.jurayev.academy.service;

import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.StudentRequest;
import uz.jurayev.academy.rest.response.StudentResponse;
import java.security.Principal;
import java.util.List;

public interface StudentService {

    Result save(StudentRequest request);
    Result delete(Integer id);
    List<StudentResponse> getStudents(Principal principal);
    StudentResponse getStudentById(Integer id);
    Result updateStudent(StudentRequest request, Integer id);
}
