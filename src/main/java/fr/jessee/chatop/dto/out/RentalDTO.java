package fr.jessee.chatop.dto.out;

import lombok.Data;

@Data
public class RentalDTO {
    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Long ownerId;
}
