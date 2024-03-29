package kz.message_project.userProject.services;

import kz.message_project.userProject.client.FileSysyemClient;
import kz.message_project.userProject.dto.ImageDto;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.mapper.UserMapper;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FileSysyemClient fileSysyemClient;
    private final UserMapper userMapper;

    public UserDto getUser(Long id){
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElse(null);

        if (Objects.nonNull(user)){
            byte [] image = fileSysyemClient.downloadFromMinio(user.getImageMinioName());
            userDto = userMapper.mapToUserDto(user);
            userDto.setImage(image);
        }
        return userDto;
    }

    public UserDto createUser(User user, MultipartFile image){
        if(Objects.isNull(user)){
            throw new NullPointerException("The User should not be null");
        }
        String imageMinioName = fileSysyemClient.uploadToMinio(image);
        user.setImageMinioName(imageMinioName);
        var savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(User user) {
        var savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
