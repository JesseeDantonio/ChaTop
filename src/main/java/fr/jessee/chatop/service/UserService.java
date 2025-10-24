package fr.jessee.chatop.service;

import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity updateUser(Integer id, UserEntity user) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setUpdatedAt(LocalDate.now().toString());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}