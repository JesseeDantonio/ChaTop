package fr.jessee.chatop.dto.in;

import lombok.Data;

@Data
public class MessageCreateDTO {
    private String content;
    private Integer userId;
    private Integer rentalId;
    private String timestamp;
}
