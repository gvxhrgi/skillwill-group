package com.example.Skillwill_Group_Work.controller;

import com.example.Skillwill_Group_Work.models.User;
import com.example.Skillwill_Group_Work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
