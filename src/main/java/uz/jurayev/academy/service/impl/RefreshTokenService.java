package uz.jurayev.academy.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.RefreshToken;
import uz.jurayev.academy.domain.User;
import uz.jurayev.academy.exception.TokenRefreshException;
import uz.jurayev.academy.repository.RefreshTokenRepository;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.security.JwtUtils;
import uz.jurayev.academy.security.SecurityConstant;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String secretKey;

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }



    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            tokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
       return tokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

}
