package com.Versetti.AuthChallenge.controllers;

import com.Versetti.AuthChallenge.dtos.UserDto;
import com.Versetti.AuthChallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers () {
        Map<String, Object> response = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<?> createNewLogin (@RequestBody UserDto userDTO) {
        Map<String, Object> response = userService.saveNewLogin(userDTO);
        if (response.containsKey("message")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
