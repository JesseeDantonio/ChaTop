package fr.jessee.chatop.repository;

import fr.jessee.chatop.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Integer> {
    Optional<RefreshTokenEntity> findByToken(String token);
    int deleteByUserId(Long userId);
}
