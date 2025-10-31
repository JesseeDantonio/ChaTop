package fr.jessee.chatop.dto.in.auth;

import lombok.Data;

@Data
public class UserAuthDTO {
    private String email;
    private String password;
}
