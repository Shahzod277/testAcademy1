package uz.jurayev.academy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.GroupRequest;
import uz.jurayev.academy.rest.request.StudentRequest;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.service.impl.TutorStudentServiceImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tutor/api/student")
@RequiredArgsConstructor
@CrossOrigin
@SecurityRequirement(name = "academy-backend")
public class TutorStudentController {

    private final TutorStudentServiceImpl studentService;

    @PostMapping
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<Result> createStudent(@Valid @RequestBody StudentRequest request) {
        Result save = studentService.save(request);
        return ResponseEntity.status(save.getSuccess() ? 200 : 400).body(save);
    }

    @GetMapping
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> getAllStudents(@RequestParam(value = "page",defaultValue = "1")Integer page ,
                                        @RequestParam(value = "limit",defaultValue = "4")Integer limit  , Principal principal) {

        List<StudentResponse> studentInfoDtos = studentService.getStudents(principal,page,limit);
        return ResponseEntity.status(studentInfoDtos != null ? 200 : 404).body(studentInfoDtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> getStudentById(@PathVariable Integer id) {
        StudentResponse studentById = studentService.getStudentById(id);
        return ResponseEntity.status(studentById != null ? 200 : 404).body(studentById);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> deleteStudent(@PathVariable Integer id) {
        Result delete = studentService.delete(id);
        return ResponseEntity.status(delete.getSuccess() ? 200 : 409).body(delete);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest request) {
        Result update = studentService.updateStudent(request, id);
        return ResponseEntity.status(update.getSuccess() ? 200 : 400).body(update);
    }

    @GetMapping("/groups")
    public ResponseEntity<?> getAllGroups(Principal principal) {
        List<GroupRequest> groups = studentService.getGroups(principal);
        return ResponseEntity.status(groups != null ? 200 : 400).body(groups);
    }

    @GetMapping("/bygroup")
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> getAllStudentsByGroup(@RequestBody GroupRequest request) {
        List<StudentResponse> studentInfoDtos = studentService.getAllStudentByGroup(request.getGroupName());
        return ResponseEntity.status(studentInfoDtos != null ? 200 : 404).body(studentInfoDtos);
    }

    @GetMapping("/search/{word}")
    @PreAuthorize("hasRole('TUTOR')")
    public HttpEntity<?> SearchStudent(@PathVariable String word,Principal principal) {
        List<StudentResponse> studentInfoDtos = studentService.searchStudent(word,principal);
        return ResponseEntity.status(studentInfoDtos != null ? 200 : 404).body(studentInfoDtos);
    }
}
