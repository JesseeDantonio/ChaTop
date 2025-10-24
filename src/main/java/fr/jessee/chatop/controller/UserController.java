package fr.jessee.chatop.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
public class UserController {
    // Route GET /api/users
    @GetMapping
    public String getAllUsers() {
        return "Liste de tous les utilisateurs";
    }

    // Route GET /api/users/{id}
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "Détail de l'utilisateur avec l'id " + id;
    }

    // Route POST /api/users
    @PostMapping
    public String createUser(@RequestBody String location) {
        return "Utilisateur créé : " + location;
    }

    // Route PUT /api/users/{id}
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody String location) {
        return "Utilisateur modifié : " + location;
    }

    // Route DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Utilisateur supprimé avec l'id " + id;
    }
}
