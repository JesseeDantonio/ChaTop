package fr.jessee.chatop.service;

import fr.jessee.chatop.dto.in.UserCreateDTO;
import fr.jessee.chatop.dto.out.UserDTO;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO getUserById(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return toDTO(user.get());
    }

    public UserCreateDTO createUser(UserCreateDTO user) {
        userRepository.save(toEntity(user));
        return user;
    }

    public UserDTO updateUser(Integer id, UserCreateDTO user) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setUpdatedAt(LocalDate.now().toString());
            userRepository.save(existingUser);
            return toDTO(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public UserEntity toEntity(UserCreateDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}