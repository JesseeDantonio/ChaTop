package fr.jessee.chatop.controller;

import fr.jessee.chatop.dto.in.UserCreateDTO;
import fr.jessee.chatop.dto.out.UserDTO;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    // Route GET /api/users
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserCreateDTO userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
