package fr.jessee.chatop.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDTO {
    private Long id;
    private String message;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("rental_id")
    private Long rentalId;
    private String timestamp;
}
