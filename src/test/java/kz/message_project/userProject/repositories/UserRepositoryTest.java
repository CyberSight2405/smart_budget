package kz.message_project.userProject.repositories;

import jakarta.transaction.Transactional;
import kz.message_project.userProject.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static kz.message_project.userProject.entity.Role.ADMIN;
import static kz.message_project.userProject.entity.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace  = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User user = User.builder()
                .username("Nyos")
                .phone("87071019309")
                .role(ADMIN)
                .email("islam_vip02@mail.ru")
                .password("qwerty123")
                .surname("Uchiha")
                .name("Madara")
                .build();
    User user2 = User.builder()
            .username("Purple")
            .phone("87071019309")
            .role(USER)
            .email("nyos777@mail.ru")
            .password("zxc123")
            .surname("Uchiha")
            .name("Sasuke")
            .build();

    @BeforeEach
    void createUsers(){
        userRepository.deleteAll();
    }

    @Test
    public void UserRepository_findByUsername_ReturnsSavedUser() {
        // Действия(методы) которые тестируем
        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("Nyos");
        assertThat(savedUser.getPassword()).isEqualTo("qwerty123");
    }

    @Test
    public void UserRepository_findAll_ReturnMoreThenOneUser(){
        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);

    }

    @Test
    public void UserRepository_findById_ReturnUser(){
        User savedUser = userRepository.save(user);

        User userResult = userRepository.findById(savedUser.getId()).orElse(null);

        assertThat(userResult).isNotNull();
    }

    @Test
    public void UserRepository_findByUsername_ReturnUser(){
        userRepository.save(user);

        User userResult = userRepository.findByUsername(user.getUsername()).orElse(null);

        assertThat(userResult).isNotNull();
    }

    @Test
    public void UserRepository_updateUser_ReturnUpdatedUser(){
        User savedUser = userRepository.save(user);

        User user1 = userRepository.findById(savedUser.getId()).orElse(null);

        assertThat(savedUser).isNotNull();

        savedUser.setName("Obito");
        savedUser.setPhone("777888999");
        User updatedUser = userRepository.save(savedUser);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo("Obito");
        assertThat(updatedUser.getPhone()).isEqualTo("777888999");
    }

    @Test
    public void UserRepository_UserDeleteById_ReturnEmptyUsersList(){
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        List<User> userList = userRepository.findAll();

        assertThat(userList).isEmpty();
    }
}