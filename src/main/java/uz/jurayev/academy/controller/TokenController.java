package uz.jurayev.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jurayev.academy.domain.ResponseToken;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.service.impl.TokenServiceImpl;

@RestController
@RequestMapping("api/student/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenServiceImpl tokenService;

    @GetMapping("get")
    public ResponseEntity<ResponseToken> getToken() {

        return ResponseEntity.ok(tokenService.getToken());
    }

//    @PostMapping
//    public ResponseEntity<Result> saveToken(ResponseToken responseToken){
//
//        Result result = tokenService.save(responseToken);
//        return ResponseEntity.status(result.getSuccess() ? 200 : 204).body(result);
//    }
}
