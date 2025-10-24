package fr.jessee.chatop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Long ownerId;
    private String createdAt;
    private String updatedAt;
}