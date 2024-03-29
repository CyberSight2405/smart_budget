package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.client.FileSysyemClient;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.mapper.UserMapper;
import kz.message_project.userProject.repositories.UserRepository;
import kz.message_project.userProject.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private UserMapper userMapper;

    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.11"));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @SneakyThrows
    @Test
    void findById() {
        UserDto userDto = UserDto.builder()
                .username("nyos")
                .password("qwerty")
                .email("nyos@gmail.com")
                .build();
        when(userService.getUser(1L)).thenReturn(userDto);

        // Act
        ResponseEntity<UserDto> responseEntity = userController.findById(1L);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(userDto);
    }

    @Test
    @SneakyThrows
    void getAllUsers() {
        var userList = mockMvc.perform(get("http://localhost:8082/api/user"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        userList.getResponse().getContentAsString();
        assertThat(userList.getResponse().getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(userRepository.findAll()));
    }
}