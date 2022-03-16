package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.RefreshToken;
import uz.jurayev.academy.exception.TokenRefreshException;
import uz.jurayev.academy.repository.RefreshTokenRepository;
import uz.jurayev.academy.repository.UserRepository;
import uz.jurayev.academy.security.SecurityConstant;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(SecurityConstant.REFRESH_TOKEN_EXPIRE_AT));
        refreshToken.setToken(UUID.randomUUID().toString());
        return tokenRepository.save(refreshToken);
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
