package uz.jurayev.academy.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.jurayev.academy.domain.RefreshToken;
import uz.jurayev.academy.exception.TokenRefreshException;
import uz.jurayev.academy.response.JwtResponse;
import uz.jurayev.academy.rest.request.LoginRequest;
import uz.jurayev.academy.rest.request.TokenRefreshRequest;
import uz.jurayev.academy.rest.response.TokenRefreshResponse;
import uz.jurayev.academy.security.JwtUtils;
import uz.jurayev.academy.service.impl.RefreshTokenService;
import uz.jurayev.academy.service.impl.UserDetailsImpl;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, RedirectAttributes redirectAttributes, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String refreshToken = jwtUtils.createRefreshToken(((UserDetailsImpl) authentication.getPrincipal()).getEmail());
            return ResponseEntity.ok(new JwtResponse(
                    token,
                    refreshToken,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles

            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).body("password or email error");
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) throws Exception {
        String requestRefreshToken = request.getRefreshToken();
        String username = jwtUtils.getUsernameFromJwtToken(requestRefreshToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String responseRefreshToken = jwtUtils.createRefreshToken(username);
        String token = jwtUtils.generateTokenFromUsername(username);
        return ResponseEntity.ok(new JwtResponse(
                token,
                responseRefreshToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
