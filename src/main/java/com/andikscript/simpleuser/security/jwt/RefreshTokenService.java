package com.andikscript.simpleuser.security.jwt;

import com.andikscript.simpleuser.exception.TokenRefreshException;
import com.andikscript.simpleuser.model.RefreshToken;
import com.andikscript.simpleuser.repository.RefreshTokenRepository;
import com.andikscript.simpleuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${SimpleUser.app.jwtRefreshExpiration}")
    private Long refreshTokenDuration;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer id) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setIdUser(userRepository.findById(id).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiredDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiredDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired");
        }
        return refreshToken;
    }
}
