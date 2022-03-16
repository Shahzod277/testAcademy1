package uz.jurayev.academy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.academy.model.Result;
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

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequestDto){
        UserResponse user = userService.create(userRequestDto);
        return ResponseEntity.status(user!=null ? 201 : 401).body(user);
    }

    @GetMapping
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        UserResponse userResponseDto = userService.getById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequest requestDto){
        Result result = userService.edit(id, requestDto);

            return ResponseEntity.status(result.getSuccess() ? 200 : 204).body(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("{hasRole('ADMIN')}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Result result = userService.delete(id);

        return ResponseEntity.status(result.getSuccess() ? 200 : 204).body(result);
    }
}
