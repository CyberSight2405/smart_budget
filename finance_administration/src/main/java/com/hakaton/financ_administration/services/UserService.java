package com.hakaton.financ_administration.services;

import com.hakaton.financ_administration.dto.UserDto;
import com.hakaton.financ_administration.entity.User;
import com.hakaton.financ_administration.mapper.UserMapper;
import com.hakaton.financ_administration.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public UserDto getUser(Long id){
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElse(null);

        if (Objects.nonNull(user)){
            userDto = userMapper.mapToUserDto(user);
        }
        return userDto;
    }

    public UserDto createUser(User user, MultipartFile image){
        if(Objects.isNull(user)){
            throw new NullPointerException("The User should not be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(User user) {
        var savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
