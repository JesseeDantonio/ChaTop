package fr.jessee.chatop.dto.out.auth;

import lombok.Data;

@Data
public class TokenDTO {
    private final String token;
    private final String refresh_token;
}
