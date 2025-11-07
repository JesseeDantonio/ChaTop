package fr.jessee.chatop.controller;

import fr.jessee.chatop.dto.in.RentalCreateDTO;
import fr.jessee.chatop.dto.out.RentalDTO;
import fr.jessee.chatop.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // GET /api/rentals
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // GET /api/rentals/{id}
    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    // POST /api/rentals
    @PostMapping
    public ResponseEntity<RentalCreateDTO> createRental(
            @RequestParam String name,
            @RequestParam Double surface,
            @RequestParam Double price,
            @RequestParam String description,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam Long ownerId
    ) throws IOException {
        // 1. Stocker l'image sur le disque
        String uploadDir = "uploads/";
        String fileName = UUID.randomUUID() + "_" + picture.getOriginalFilename();
        Files.createDirectories(Paths.get(uploadDir));
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(picture.getInputStream(), filePath);

        // 2. Préparer le DTO à envoyer au service
        RentalCreateDTO dto = new RentalCreateDTO();
        dto.setName(name);
        dto.setSurface(surface);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setPicture("/uploads/" + fileName);
        dto.setOwnerId(ownerId);

        // 3. Appeler le service
        RentalCreateDTO created = rentalService.createRental(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/rentals/{id}
    @PutMapping("/{id}")
    public RentalDTO updateRental(@PathVariable int id, @RequestBody RentalDTO rental) {
        return rentalService.updateRental(id, rental);
    }

    // DELETE /api/rentals/{id}
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }
}
