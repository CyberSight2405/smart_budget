package kz.message_project.userProject.services;

import kz.message_project.userProject.client.FileSysyemClient;
import kz.message_project.userProject.dto.ImageDto;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final FileSysyemClient fileSysyemClient;

    public UserDto getUser(Long id){
        UserDto userDto = new UserDto();
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.orElse(null);
        if (Objects.nonNull(user)){
            byte [] image = fileSysyemClient.downloadFromMinio(user.getImageMinioName());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setUsername(user.getUsername());
            userDto.setPhone(user.getPhone());
            userDto.setEmail(user.getEmail());
            userDto.setImage(image);
        }
        return userDto;
    }

    public void createUser(User user, MultipartFile image){
        String imageMinioName = image.getOriginalFilename() + "_" + UUID.randomUUID();

        fileSysyemClient.uploadToMinio(image);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private ImageDto convertToImageDto(MultipartFile image){
        ImageDto imageDto = new ImageDto();
        try {
            imageDto.setBytes(image.getBytes());
            imageDto.setSize(image.getSize());
            imageDto.setContentType(image.getContentType());
            imageDto.setOriginalFileName(image.getOriginalFilename());
            return imageDto;
        } catch (IOException e) {
            log.error("Error when convert to ImageDto: {}", e.getMessage());
        }
        return null;
    }
}
