package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.repositories.UserRepository;
import kz.message_project.userProject.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest
@Testcontainers
@DisplayName("Integration test for AuthenticationController")
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private  ObjectMapper objectMapper;

    String token;


    @InjectMocks
    private UserController userController;
    @Mock
    private UserRepository userRepository;

    private final String baseUrl = "http://localhost:8082/api/v1/auth";


    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.11-alpine"));

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.username", postgres::getUsername);
    }

    @BeforeEach
    void setUp() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(post(baseUrl + "/login")
                        .with(httpBasic("nyos", "qwerty123")));
        MvcResult mvcResult = resultActions.andDo(print()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(contentAsString);
        this.token = "Bearer " + jsonObject.getString("token");
    }

    @SneakyThrows
    @Test
    void testRegister(){
        UserDto userDto = UserDto.builder()
                .username("nyos")
                .email("nyos@gmail.com")
                .password("qwerty")
                .build();

        String UserDtoJson = objectMapper.writeValueAsString(userDto);

        this.mockMvc.perform(post(this.baseUrl + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", this.token)
                        .content(UserDtoJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.username").value("nyos"))
                .andExpect(jsonPath("$.email").value("nyos@gmail.com"))
                .andReturn();
    }

    @SneakyThrows
    @Test
    void testLogin() {
        User user = User.builder()
                .username("nyos")
                .name("nyos")
                .surname("surname")
                .email("email@mail.ru")
                .password("qwerty")
                .build();

        when(userRepository.findByUsername("nyos")
                .orElseThrow()).thenReturn(user);

        String UserDtoJson = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post(baseUrl + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UserDtoJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("nyos"))
                .andExpect(jsonPath("$.email").value("email@mail.ru"))
                .andExpect(jsonPath("$.name").value("nyos"))
                .andReturn();
    }
}