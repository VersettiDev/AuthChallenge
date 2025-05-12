package com.Versetti.AuthChallenge.services;

import com.Versetti.AuthChallenge.domain.User.User;
import com.Versetti.AuthChallenge.dtos.AuthDto;
import com.Versetti.AuthChallenge.dtos.UserDto;
import com.Versetti.AuthChallenge.infraestructure.utilities.Base64Utils;
import com.Versetti.AuthChallenge.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Map<String, Object> getAllUsers () {
        Map<String, Object> response = new HashMap<>();
        response.put("users", userRepository.findAll());
        return response;
    }

    public Map<String, Object> saveNewLogin (UserDto userDTO) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            response.put("message", "Username " + userDTO.username().toUpperCase() + " already exists");
            return response;
        };

        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            response.put("message", "Email " + userDTO.email() + " already exists");
            return response;
        }

        User userData = new User();
        BeanUtils.copyProperties(userDTO, userData);
        userData.setPassword(passwordEncoder.encode(userDTO.password()));
        userRepository.save(userData);

        userData.setPassword(Base64Utils.encode(userDTO.password()));
        response.put("message", "User created successfully");
        response.put("user", userData);
        return response;

    }

    public Map<String, Object> validateAuthentication (AuthDto authDTO) {
        Map<String, Object> response = new HashMap<>();
        if (userRepository.findByUsername(authDTO.username()).isPresent()) {
            User userData = userRepository.findByUsername(authDTO.username()).get();
            String decodedPassword = Base64Utils.decode(authDTO.password());
            if (passwordEncoder.matches(decodedPassword, userData.getPassword())) {
                response.put("message", "Authentication successful");
                return response;
            }
        }

        response.put("error", "Username or password is incorrect");
        return response;
    }
}
