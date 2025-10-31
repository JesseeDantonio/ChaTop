package fr.jessee.chatop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private RentalEntity rentalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    private String timestamp;

    private String message;

    private String createdAt;

    private String updatedAt;
}