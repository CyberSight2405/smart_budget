    package kz.message_project.userProject.mapper;

    import kz.message_project.userProject.dto.UserDto;
    import kz.message_project.userProject.entity.User;
    import lombok.RequiredArgsConstructor;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.test.context.DynamicPropertyRegistry;
    import org.springframework.test.context.DynamicPropertySource;
    import org.testcontainers.containers.PostgreSQLContainer;
    import org.testcontainers.junit.jupiter.Container;
    import org.testcontainers.junit.jupiter.Testcontainers;
    import org.testcontainers.utility.DockerImageName;


    import static org.assertj.core.api.Assertions.assertThat;
    import static org.junit.jupiter.api.Assertions.*;

    @SpringBootTest
    @Testcontainers
    @RequiredArgsConstructor
    class UserMapperTest {

        @InjectMocks
        private UserMapper userMapper;

        @Container
        static private final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.11"));

        @DynamicPropertySource
        static void setProperties(DynamicPropertyRegistry registry){
            registry.add("spring.datasource.url", postgres::getJdbcUrl);
            registry.add("spring.datasource.username", postgres::getUsername);
            registry.add("spring.datasource.password", postgres::getPassword);
        }

        @Test
        void ShouldMapUserDTOToUser() {
            UserDto userDto = UserDto.builder()
                    .username("username")
                    .password("pass")
                    .email("email")
                    .name("name")
                    .phone("12345")
                    .build();

            User user = userMapper.mapToUser(userDto);

            assertThat(user).isNotNull();
            assertThat(user.getId()).isEqualTo(userDto.getId());
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getName(), user.getName());
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getPhone(), user.getPhone());
            assertEquals(userDto.getSurname(), user.getSurname());
        }

        @Test
        void should_throw_null_pointer_exeption_when_userDTO_is_null() {
           var message = assertThrows(NullPointerException.class, () -> userMapper.mapToUser(null));
           assertEquals("The UserDto should not be null", message.getMessage());
        }

        @Test
        void ShouldMapUserToUserDto() {
            User user = new User(1L,
                    "username",
                    "name",
                    "surname",
                    "pass",
                    "email");

            UserDto userDto = userMapper.mapToUserDto(user);

            assertThat(userDto).isNotNull();
            assertEquals(user.getId(), userDto.getId());
            assertEquals(user.getUsername(), userDto.getUsername());
            assertEquals(user.getName(), userDto.getName());
            assertEquals(user.getSurname(), userDto.getSurname());
            assertEquals(user.getEmail(), userDto.getEmail());
            assertEquals(user.getPhone(), userDto.getPhone());
        }
    }