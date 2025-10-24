package fr.jessee.chatop.service;

import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalEntity> getAllRentals() {
        return rentalRepository.findAll();
    }

    public RentalEntity getOwnerById(Integer id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public RentalEntity getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public RentalEntity createRental(RentalEntity message) {
        return rentalRepository.save(message);
    }

    public RentalEntity updateRental(Integer id, RentalEntity rental) {
        RentalEntity existingRental = rentalRepository.findById(id).orElse(null);
        if (existingRental != null) {
            existingRental.setName(rental.getName());
            existingRental.setDescription(rental.getDescription());
            existingRental.setPrice(rental.getPrice());
            existingRental.setPicture(rental.getPicture());
            existingRental.setSurface(rental.getSurface());
            existingRental.setUpdatedAt(LocalDate.now().toString());
            return rentalRepository.save(existingRental);
        } else {
            return null;
        }
    }

    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }
}