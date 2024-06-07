package com.example.online_shop.services;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.User;
import com.example.online_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(UserDto userDto) throws Exception {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new Exception("Email already in use");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole("USER");

        userRepository.save(user);
    }

    public boolean verifyPassword(String rawPassword, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
}
