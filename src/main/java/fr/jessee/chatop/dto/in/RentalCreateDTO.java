package fr.jessee.chatop.dto.in;

import lombok.Data;

@Data
public class RentalCreateDTO {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Integer ownerId;
}
