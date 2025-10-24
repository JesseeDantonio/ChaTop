package fr.jessee.chatop.repository;

import fr.jessee.chatop.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    MessageEntity findByRentalId(int rentalId);

    MessageEntity findByUserId(int userId);
}
