package fr.jessee.chatop.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private Integer id;
    private String content;
    private Integer senderId;
    private Integer receiverId;
    private String timestamp;
}
