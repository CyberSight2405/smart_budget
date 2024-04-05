package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private  ObjectMapper objectMapper;

    @Mock
    private UserRepository userRepository;

    private final String baseUrl = "http://localhost:8082/api/v1/auth";

    @BeforeEach
    void setUp() throws Exception {
        UserDto userDto = UserDto.builder()
                .username("nyos")
                .email("nyos@gmail.com")
                .name("Islam")
                .password("qwerty123")
                .build();

        String userDtoJson = objectMapper.writeValueAsString(userDto);

        MvcResult mvcResult = this.mockMvc
                .perform(post(baseUrl + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(contentAsString);
    }

//    @SneakyThrows
//    @Test
//    void testLogin() {
//        User user = User.builder()
//                .username("nyos")
//                .name("nyos")
//                .surname("surname")
//                .email("email@mail.ru")
//                .password("qwerty123")
//                .build();
//
//        when(userRepository.findByUsername("nyos")
//                .orElseThrow()).thenReturn(user);
//
//        String UserDtoJson = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(post(baseUrl + "/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(httpBasic("nyos", "qwerty123"))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("nyos"))
//                .andExpect(jsonPath("$.email").value("email@mail.ru"))
//                .andExpect(jsonPath("$.name").value("nyos"))
//                .andReturn();
//    }
}