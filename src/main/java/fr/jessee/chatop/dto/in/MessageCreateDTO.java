package fr.jessee.chatop.dto.in;

import lombok.Data;

@Data
public class MessageCreateDTO {
    private String message;
    private Integer userId;
    private Integer rentalId;
}
