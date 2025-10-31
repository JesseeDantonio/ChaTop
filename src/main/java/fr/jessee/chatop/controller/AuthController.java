package fr.jessee.chatop.controller;

import fr.jessee.chatop.dto.in.UserCreateDTO;
import fr.jessee.chatop.dto.in.auth.UserAuthDTO;
import fr.jessee.chatop.dto.out.UserDTO;
import fr.jessee.chatop.dto.out.auth.TokenDTO;
import fr.jessee.chatop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Inscription
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody UserCreateDTO userCreateDTO) {
        try {
            TokenDTO tokens = authService.register(userCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokens);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserAuthDTO userAuthDTO) {
        try {
            TokenDTO tokens = authService.login(userAuthDTO);
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Infos utilisateur (profil)
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(@RequestParam String email) {
        try {
            UserDTO userDTO = authService.me(email);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}