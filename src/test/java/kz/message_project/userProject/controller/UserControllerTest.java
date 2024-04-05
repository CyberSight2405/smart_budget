package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.client.FileSysyemClient;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.mapper.UserMapper;
import kz.message_project.userProject.repositories.UserRepository;
import kz.message_project.userProject.services.JwtService;
import kz.message_project.userProject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;

import static kz.message_project.userProject.entity.Role.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private UserController userController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Mock
    private FileSysyemClient fileSysyemClient;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserMapper userMapper;

    @Autowired
    private  ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:8082/api/user";

    private String token;

    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.11"));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @SneakyThrows
    @BeforeEach
    void setUp() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("nyos")
                .password("qwerty")
                .email("nyos@gmail.com")
                .name("Islam")
                .role(ADMIN)
                .build();

        User user = User.builder()
                .id(1L)
                .username("nyos")
                .password("qwerty")
                .email("nyos@gmail.com")
                .name("Islam")
                .role(ADMIN)
                .build();

        String userDtoJson = objectMapper.writeValueAsString(userDto);

        when(userRepository.findByUsername("nyos")).thenReturn(Optional.of(user));

        MvcResult mvcResult = this.mockMvc
                .perform(post("http://localhost:8082/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk()) // Проверяем, что статус ответа 200 (OK)
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(contentAsString);
        token = "Bearer " + jsonObject.getString("token");
    }



    @SneakyThrows
    @Test
    void findById() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .username("nyos")
                .email("nyos@gmail.com")
                .name("Islam")
                .build();

        User user = User.builder()
                .username("nyos")
                .email("nyos@gmail.com")
                .name("Islam")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(fileSysyemClient.downloadFromMinio(user.getImageMinioName())).thenReturn("image".getBytes());
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        var userList = mockMvc.perform(get(baseUrl+ "/1")
                        .header("Authorization", token)     )
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String responseUser = userList.getResponse().getContentAsString();

        // Assert
        assertThat(responseUser).isEqualTo(new ObjectMapper().writeValueAsString(userDto));
    }

//    @Test
//    @SneakyThrows
//    void getAllUsers() {
//
//        when(userRepository.findAll()).thenReturn(List.of());
//
//        var userList = mockMvc.perform(get(baseUrl)
//                        .header("Authorization", this.token))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//
//        userList.getResponse().getContentAsString();
//        assertThat(userList.getResponse().getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(userRepository.findAll()));
//    }
}