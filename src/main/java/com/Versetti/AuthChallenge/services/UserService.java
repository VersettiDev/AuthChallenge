package com.Versetti.AuthChallenge.services;

import com.Versetti.AuthChallenge.domain.User.User;
import com.Versetti.AuthChallenge.dtos.UserDto;
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
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepository.save(userData);

    }
}
