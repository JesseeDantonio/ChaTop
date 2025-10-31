package fr.jessee.chatop.service;

import fr.jessee.chatop.dto.in.UserCreateDTO;
import fr.jessee.chatop.dto.out.UserDTO;
import fr.jessee.chatop.dto.out.auth.TokenDTO;
import fr.jessee.chatop.entity.RefreshTokenEntity;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.feature.JsonWebToken;
import fr.jessee.chatop.repository.RefreshTokenRepository;
import fr.jessee.chatop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class AuthService {
    // 15 min en ms
    long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;
    // Refresh token : 14 jours
    long REFRESH_TOKEN_EXPIRATION = 14 * 24 * 60 * 60 * 1000;
    // Email verification : 1 heure
    long EMAIL_VERIFICATION_EXPIRATION = 60 * 60 * 1000;

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepo;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepo = refreshTokenRepo;
    }

    public TokenDTO register(UserCreateDTO user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        TokenDTO dto = new TokenDTO(
                JsonWebToken.generateToken(user.getName() , ACCESS_TOKEN_EXPIRATION),
                JsonWebToken.generateToken(user.getName() , REFRESH_TOKEN_EXPIRATION)
        );

        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setUpdatedAt(LocalDate.now().toString());
        userEntity.setCreatedAt(LocalDate.now().toString());

        userRepo.save(userEntity);

        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(userEntity);
        refreshToken.setToken(dto.getRefresh_token());
        refreshToken.setExpirationDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION));

        refreshTokenRepo.save(refreshToken);

        return dto;
    }


    public TokenDTO login(String email, String password) {
        UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String accessToken = JsonWebToken.generateToken(user.getEmail(), ACCESS_TOKEN_EXPIRATION);
        String refreshTokenStr = JsonWebToken.generateToken(user.getEmail(), REFRESH_TOKEN_EXPIRATION);

        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(user);
        refreshToken.setToken(refreshTokenStr);
        refreshToken.setExpirationDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION));
        refreshTokenRepo.save(refreshToken);

        return new TokenDTO(accessToken, refreshTokenStr);
    }

    public UserDTO me(String email) {
        UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setId(user.getId());
        return userDTO;
    }
}
