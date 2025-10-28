package fr.jessee.chatop.controller;

import fr.jessee.chatop.dto.in.RentalCreateDTO;
import fr.jessee.chatop.dto.out.RentalDTO;
import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // GET /api/rentals
    @GetMapping
    public List<RentalEntity> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // GET /api/rentals/{id}
    @GetMapping("/{id}")
    public RentalEntity getRentalById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    // POST /api/rentals
    @PostMapping
    public RentalCreateDTO createRental(@RequestBody RentalCreateDTO rental) {
        return rentalService.createRental(rental);
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
