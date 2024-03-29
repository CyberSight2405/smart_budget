package kz.message_project.userProject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String username;
    private String email;
    private String name;
    private String phone;
    private String surname;
    private String token;
}
