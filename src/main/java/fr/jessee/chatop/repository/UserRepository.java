package fr.jessee.chatop.repository;

import fr.jessee.chatop.dto.in.UserCreateDTO;
import fr.jessee.chatop.dto.out.UserDTO;
import fr.jessee.chatop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserCreateDTO> findByEmail(String email);
    Optional<UserEntity> findById(Long id);
}
