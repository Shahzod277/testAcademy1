package uz.jurayev.academy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.academy.exception.UserErrorMessage;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.rest.response.UserResponse;
import uz.jurayev.academy.service.impl.AdminUserServiceImpl;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "academy-backend")
@CrossOrigin
public class AdminUserController {

    private final AdminUserServiceImpl userService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request, @RequestHeader(value = "User-Agent") String a) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(UserErrorMessage.EMAIL_IS_EXISTS, HttpStatus.CONFLICT);
        } else if (userRepository.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>(UserErrorMessage.USERNAME_IS_EXISTS, HttpStatus.CONFLICT);
        } else if (userRepository.existsByUserProfile_PhoneNumber(request.getProfile().getPhoneNumber())) {
            return new ResponseEntity<>(UserErrorMessage.PHONE_NUMBER_IS_EXISTS, HttpStatus.CONFLICT);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("User-Agent", a);
            UserResponse userResponse = userService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(userResponse);
        }

    }

    @GetMapping
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        UserResponse userResponseDto = userService.getById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequest requestDto) {

            Result edit = userService.edit(id, requestDto);
            return ResponseEntity.ok(edit);
        }


    @DeleteMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Result result = userService.delete(id);

        return ResponseEntity.status(result.getSuccess() ? 200 : 204).body(result);
    }
}
