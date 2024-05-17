package com.hakaton.financ_administration.dto.response;

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
