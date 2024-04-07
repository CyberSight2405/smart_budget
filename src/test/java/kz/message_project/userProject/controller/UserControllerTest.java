package kz.message_project.userProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.Role;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.services.JwtService;
import kz.message_project.userProject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void init(){
        user = User.builder().name("Nyos").role(Role.ADMIN).username("NyosPurple").surname("Purple").email("islam_vip02@mail.ru").password("qwerty123").build();
        userDto = UserDto.builder().name("Nyos").role(Role.USER).username("NyosPurple").surname("Purple").email("islam_vip02@mail.ru").password("qwerty123").build();

    }

    @Test
    @WithMockUser(username = "Nyos", roles = {"USER", "ADMIN"})
    public void UserController_getAllUsers_ReturnedUserList() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "Nyos", roles = {"USER", "ADMIN"})
    public void UserController_createUser_ReturnCreated() throws Exception {

        //экземпляр MockMultipartFile для Json
        MockMultipartFile jsonFile = new MockMultipartFile(
                "userJson",
                "json.json",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(userDto).getBytes(StandardCharsets.UTF_8)
        );

        //экземпляр MockMultipartFile для файла
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "file.png",
                "multipart/form-data",
                "file content".getBytes(StandardCharsets.UTF_8)
        );

        // Создайте многокомпонентный запрос
        MockMultipartHttpServletRequestBuilder multipart = MockMvcRequestBuilders.multipart("/api/user");
        multipart.file(jsonFile);
        multipart.file(file);

        // Выполните запрос
         ResultActions response = mockMvc.perform(multipart);

         response.andExpect(MockMvcResultMatchers.status().isCreated())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(userDto.getUsername()))
                 .andDo(MockMvcResultHandlers.print());


    }
}
