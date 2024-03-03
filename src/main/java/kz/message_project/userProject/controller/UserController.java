package kz.message_project.userProject.controller;

import kz.message_project.userProject.dto.UserDto;
import kz.message_project.userProject.entity.User;
import kz.message_project.userProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@ModelAttribute User user, @RequestParam("file") MultipartFile file){
        userService.createUser(user, file);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
}
