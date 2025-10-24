package fr.jessee.chatop.repository;

import fr.jessee.chatop.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
    RentalEntity findById(Long id);
}
