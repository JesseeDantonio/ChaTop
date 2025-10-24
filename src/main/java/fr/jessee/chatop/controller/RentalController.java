package fr.jessee.chatop.controller;

import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;
    // Route GET /api/rentals
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalEntity> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalEntity getOwnerById(@PathVariable Integer id) {
        return rentalService.getOwnerById(id);
    }

    @PostMapping
    public RentalEntity createRental(@RequestBody RentalEntity user) {
        return rentalService.createRental(user);
    }

    @PutMapping("/{id}")
    public RentalEntity updateRental(@PathVariable Integer id, @RequestBody RentalEntity user) {
        return rentalService.updateRental(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Integer id) {
        rentalService.deleteRental(id);
    }
}
