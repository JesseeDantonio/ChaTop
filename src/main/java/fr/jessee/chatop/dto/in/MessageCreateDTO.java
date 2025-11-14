package fr.jessee.chatop.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageCreateDTO {
    private String message;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("rental_id")
    private Integer rentalId;
}
