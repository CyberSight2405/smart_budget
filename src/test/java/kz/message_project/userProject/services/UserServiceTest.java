package kz.message_project.userProject.services;

import kz.message_project.userProject.client.FileSysyemClient;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.mapper.UserMapper;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Testcontainers
@RequiredArgsConstructor
public class UserServiceTest {

    @Container
    static private final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.11"));

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FileSysyemClient fileSysyemClient;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void runContainer(){
//        Startables.deepStart(postgres);
        postgres.start();
    }

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void getUser() {
        UserDto userDto = UserDto.builder()
                .username("username")
                .password("pass")
                .email("email")
                .name("name")
                .phone("12345")
                .build();
        User user = new User(
                1L,
                "username",
                "name",
                "surname",
                "email",
                "87771234567");
        user.setImageMinioName("imageMinioName");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(fileSysyemClient.downloadFromMinio(user.getImageMinioName())).thenReturn("image".getBytes());
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        UserDto userDtoResponse = userService.getUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(fileSysyemClient, times(1)).downloadFromMinio(user.getImageMinioName());
        verify(userMapper, times(1)).mapToUserDto(user);

        assertEquals(userDto.getUsername(), userDtoResponse.getUsername());
        assertEquals(userDto.getEmail(), userDtoResponse.getEmail());
        assertEquals(userDto.getImage(), userDtoResponse.getImage());
        assertEquals(userDto.getPhone(), userDtoResponse.getPhone());
    }

    @Test
    void should_successfully_create_a_user() {
        UserDto userDto = UserDto.builder()
                .username("username")
                .password("pass")
                .email("email")
                .name("name")
                .phone("12345")
                .build();
        User user = new User(
                1L,
                "username",
                "name",
                "surname",
                "email",
                "87771234567");
        User savedUser = new User(
                1L,
                "user",
                "name",
                "surname",
                "email",
                "87771234567");
        user.setImageMinioName("imageMinioName");

        when(fileSysyemClient.uploadToMinio(any(MultipartFile.class))).thenReturn("imageMinioName");
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapToUserDto(savedUser)).thenReturn(userDto);

        //When
        UserDto userDtoResponse = userService.createUser(user, new MockMultipartFile("image", "image.jpg", "image/jpeg", "image".getBytes()));

        verify(fileSysyemClient, times(1)).uploadToMinio(any(MultipartFile.class));
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).mapToUserDto(savedUser);

        //Then
        assertEquals(userDto.getUsername(), userDtoResponse.getUsername());
        assertEquals(userDto.getEmail(), userDtoResponse.getEmail());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUser() {
        User user = new User(
                "username",
                "name",
                "surname",
                "email",
                "87771234567");
        User savedUser = new User(
                1L,
                "username",
                "name",
                "surname",
                "email",
                "87771234567");

        when(userRepository.save(user)).thenReturn(savedUser);

        var user2 = userRepository.save(user);

        verify(userRepository, times(1)).save(user);

        assertThat(user2.getId()).isEqualTo(1L);
        assertThat(user2.getName()).isEqualTo("name");
    }

    @Test
    void getAllUsers() {
        User user = new User(
                "Nyos",
                "Purple",
                "Aqua",
                "nyos@gmail.com",
                "87779876541");
        User user2 = new User(
                "username",
                "name",
                "surname",
                "email",
                "87771234567");

        when(userRepository.findAll()).thenReturn(List.of(user, user2));

        List<User> users = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0)).isEqualTo(user);
        assertThat(users.get(1)).isEqualTo(user2);
    }
}