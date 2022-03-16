package uz.jurayev.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.academy.rest.response.TutorResponse;
import uz.jurayev.academy.service.impl.TutorServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/api/tutor")
@RequiredArgsConstructor
public class TutorController {

    private final TutorServiceImpl tutorService;

    @PreAuthorize("hasRole('TUTOR')")
    @GetMapping
    public ResponseEntity<?> getAllGroup(Principal principal) {
        TutorResponse tutor = tutorService.findByTutor(principal);
        return ResponseEntity.status(tutor == null ? 404 : 200).body(tutor);
    }
}