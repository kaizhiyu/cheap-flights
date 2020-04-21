package com.cheapflights.tickets.service;

import com.cheapflights.tickets.domain.dto.UserDTO;
import com.cheapflights.tickets.domain.model.User;
import com.cheapflights.tickets.exception.UsernameTakenException;
import com.cheapflights.tickets.repository.UserRepository;
import com.cheapflights.tickets.service.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User save(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername().toLowerCase());
        if (existingUser.isPresent()) {
            throw new UsernameTakenException("Username is already taken.");
        }
        User user = userMapper.toEntity(userDTO);
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }
}