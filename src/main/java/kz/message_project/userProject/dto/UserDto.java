package kz.message_project.userProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private byte[] image;
}
