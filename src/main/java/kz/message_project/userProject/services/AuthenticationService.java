package kz.message_project.userProject.services;

import kz.message_project.userProject.dto.AuthenticationDto;
import kz.message_project.userProject.dto.AuthenticationResponse;
import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.Role;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }
}
