package com.example.online_shop.services;

import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.User;
import com.example.online_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        if(userDto.getRole().isBlank())
            user.setRole("USER");
        else
            user.setRole(userDto.getRole());

        userRepository.save(user);
    }

    public boolean verifyPassword(String rawPassword, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(item -> new UserDto(
                        item.getName(),
                        item.getEmail(),
                        item.getRole()
                ))
                .collect(Collectors.toList());
    }

    public void deleteUsertById(int id) {
        userRepository.deleteById(id);
    }

}
