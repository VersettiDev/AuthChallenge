package com.Versetti.AuthChallenge.controllers;

import com.Versetti.AuthChallenge.dtos.UserDto;
import com.Versetti.AuthChallenge.repositories.UserRepository;
import com.Versetti.AuthChallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Map<String, Object> createNewLogin (@RequestBody UserDto userDTO) {
        Map<String, Object> response = userService.saveNewLogin(userDTO);
    }
}
