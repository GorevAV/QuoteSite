package com.example.quotesite.controller;

import com.example.quotesite.domain.Quote;
import com.example.quotesite.domain.User;
import com.example.quotesite.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping({"/{userId}"})
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @PutMapping({"/{userId}"})
    public void updateUser(@PathVariable("quoteId") Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    @DeleteMapping({"/{deleteId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("deleteId") Long userId) {
        userService.deleteUser(userId);
    }
}
