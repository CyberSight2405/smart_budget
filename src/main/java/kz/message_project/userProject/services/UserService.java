package kz.message_project.userProject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import kz.message_project.userProject.client.FileSystemClient;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.mapper.UserMapper;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FileSystemClient fileSysyemClient;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public UserDto getUser(Long id){
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElse(null);

        if (Objects.nonNull(user)){
            byte [] image = fileSysyemClient.downloadFromMinio(StringUtils.isNotEmpty(user.getImageMinioName()) ? user.getImageMinioName() : "default.png");
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public String sendJsonToFileService(String jsonFile){
        return fileSysyemClient.sendAndConsumeJson(jsonFile);
    }
}
