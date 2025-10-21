package com.doodle.minidoodle.service;

import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(String name, String email) {
        UserEntity user = UserEntity.builder()
                .name(name)
                .email(email)
                .build();
        return userRepository.save(user);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
