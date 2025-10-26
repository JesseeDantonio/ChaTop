package fr.jessee.chatop.dto.out;

import lombok.Data;

@Data
public class RentalDTO {
    private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Integer ownerId;
}
