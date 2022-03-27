package uz.jurayev.academy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.rest.request.AdminTutorRequest;
import uz.jurayev.academy.rest.response.AdminTutorResponse;
import uz.jurayev.academy.service.impl.AdminTutorServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/api/tutor")
@RequiredArgsConstructor
@SecurityRequirement(name = "academy-backend")
@CrossOrigin
public class AdminTutorController {

    private final AdminTutorServiceImpl adminService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTutor(@RequestBody AdminTutorRequest tutorDto) {

        Result result = adminService.createTutor(tutorDto);
        return ResponseEntity.status(result.getSuccess() ? 201 : 400).body(result);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminTutorResponse> getAllTutors(Principal principal) {
        return adminService.getAllTutor(principal);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminTutorResponse getTutorById(@PathVariable Integer id) {
        return adminService.getTutorById(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeTutor(@PathVariable Integer id) {
        Result result = adminService.removeTutor(id);
        return ResponseEntity.status(result.getSuccess() ? 200 : 404).body(result);
    }

    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllStudents() {
        List<StudentResponse> all = adminService.getAllStudent();

        return ResponseEntity.status(!all.isEmpty() ? 200 : 401).body(all);

    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTutor(@PathVariable Integer id, @RequestBody AdminTutorRequest request) {
        Result result = adminService.updateTutor(id, request);
        return ResponseEntity.status(result.getSuccess() ? 200 : 404).body(result);
    }

}
