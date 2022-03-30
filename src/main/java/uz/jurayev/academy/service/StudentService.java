package uz.jurayev.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.StudentRequest;
import uz.jurayev.academy.rest.response.StudentResponse;

import java.security.Principal;
import java.util.List;

public interface StudentService {

    Result save(StudentRequest request);

    Result delete(Integer id);

    List<StudentResponse> getStudents(Principal principal, Integer page, Integer limit);

    StudentResponse getStudentById(Integer id);

    Result updateStudent(StudentRequest request, Integer id);
}
