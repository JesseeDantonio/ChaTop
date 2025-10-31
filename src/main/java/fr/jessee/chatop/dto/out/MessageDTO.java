package fr.jessee.chatop.dto.out;

import lombok.Data;

@Data
public class MessageDTO {
    private Long id;
    private String message;
    private Long senderId;
    private Long receiverId;
    private String timestamp;
}
