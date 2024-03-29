package kz.message_project.userProject.mapper;

import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto){
        if(userDto == null){
            throw new NullPointerException("The UserDto should not be null");
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }

    public UserDto mapToUserDto(User user){
        if(user == null){
            throw new NullPointerException("The User should not be null");
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }
}
