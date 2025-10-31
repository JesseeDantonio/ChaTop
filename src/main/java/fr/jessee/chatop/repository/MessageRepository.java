package fr.jessee.chatop.repository;

import fr.jessee.chatop.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    @Query("SELECT m FROM MessageEntity m WHERE m.userId = :userId")
    List<MessageEntity> findByUserId(@Param("userId") int userId);
    @Query("SELECT m FROM MessageEntity m WHERE m.rentalId = :rentalId")
    List<MessageEntity> findByRentalId(@Param("rentalId") int rentalId);
}
