package com.Versetti.AuthChallenge.controllers;

import com.Versetti.AuthChallenge.domain.User.User;
import com.Versetti.AuthChallenge.dtos.AuthDto;
import com.Versetti.AuthChallenge.repositories.UserRepository;
import com.Versetti.AuthChallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth/login")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> validateAuthentication (@RequestBody AuthDto authDTO) {
        Map<String, Object> response = userService.validateAuthentication(authDTO);
        if (response.containsKey("message")) {
//            return ResponseEntity.status(HttpStatus.OK).body(response);
            User userData = userRepository.findByUsername(authDTO.username()).get();

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
