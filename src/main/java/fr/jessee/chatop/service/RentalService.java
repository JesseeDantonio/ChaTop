package fr.jessee.chatop.service;

import fr.jessee.chatop.dto.in.RentalCreateDTO;
import fr.jessee.chatop.dto.out.RentalDTO;
import fr.jessee.chatop.entity.RentalEntity;
import fr.jessee.chatop.entity.UserEntity;
import fr.jessee.chatop.repository.RentalRepository;
import fr.jessee.chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository,  UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public RentalDTO getById(int id) {
        RentalEntity rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            return toDTO(rental);
        }
        return null;
    }

    public List<RentalDTO> getAllRentals() {
        List<RentalDTO> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(rental -> {
            RentalDTO dto = toDTO(rental);
            rentals.add(dto);
        });
        return rentals;

    }

    public RentalCreateDTO createRental(RentalCreateDTO rental) {
        rentalRepository.save(toEntity(rental));
        return rental;
    }

    public RentalDTO updateRental(Integer id, RentalDTO rental) {
        RentalEntity existingRental = rentalRepository.findById(id).orElse(null);
        if (existingRental != null) {
            existingRental.setName(rental.getName());
            existingRental.setDescription(rental.getDescription());
            existingRental.setPrice(rental.getPrice());
            existingRental.setPicture(rental.getPicture());
            existingRental.setSurface(rental.getSurface());
            UserEntity owner = userRepository.findById(rental.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            existingRental.setOwnerId(owner);
            existingRental.setUpdatedAt(LocalDate.now().toString());
            rentalRepository.save(existingRental);
            return toDTO(existingRental);
        } else {
            return null;
        }
    }

    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }

    private RentalDTO toDTO(RentalEntity entity) {
        RentalDTO dto = new RentalDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setSurface(entity.getSurface());
        dto.setOwnerId(entity.getOwnerId().getId());
        dto.setPicture(entity.getPicture());
        return dto;
    }

    public RentalEntity toEntity(RentalCreateDTO dto) {
        RentalEntity entity = new RentalEntity();
        entity.setName(dto.getName());
        entity.setSurface(dto.getSurface());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setPicture(dto.getPicture());
        UserEntity owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        entity.setOwnerId(owner);
        return entity;
    }
}