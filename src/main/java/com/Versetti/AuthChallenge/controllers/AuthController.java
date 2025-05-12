package com.Versetti.AuthChallenge.controllers;

import com.Versetti.AuthChallenge.domain.User.User;
import com.Versetti.AuthChallenge.dtos.AuthDto;
import com.Versetti.AuthChallenge.dtos.AuthResponse;
import com.Versetti.AuthChallenge.dtos.UserResponse;
import com.Versetti.AuthChallenge.infraestructure.utilities.JwtUtils;
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

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> validateAuthentication (@RequestBody AuthDto authDTO) {
        Map<String, Object> response = userService.validateAuthentication(authDTO);
        if (response.containsKey("message")) {
            // Create Response Dto's
            User userData = userRepository.findByUsername(authDTO.username()).get();
            String tokenAuth = jwtUtils.generateAuthToken(authDTO.username());

            UserResponse userResponse = new UserResponse(
                    userData.getId(),
                    userData.getUsername(),
                    userData.getEmail()
            );

            AuthResponse authResponse = new AuthResponse(userResponse, tokenAuth);
            return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
