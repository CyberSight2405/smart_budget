package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

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
        var userDto = mockMvc.perform(get("http://localhost:8082/api/user/1"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        assertThat(userDto.getResponse().getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(userRepository.findById(1L)));
    }

    @Test
    @SneakyThrows
    void getAllUsers() {
        var userList = mockMvc.perform(get("http://localhost:8082/api/user"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertThat(userList.getResponse().getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(userRepository.findAll()));
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}