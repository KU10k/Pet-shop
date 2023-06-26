package com.ku10k.petshop.service;

import com.ku10k.petshop.models.User;
import com.ku10k.petshop.models.enums.Role;
import com.ku10k.petshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        user.setActive(true);
        userRepository.save(user);
    }
}
