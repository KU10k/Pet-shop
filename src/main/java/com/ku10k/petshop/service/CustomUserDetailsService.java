package com.ku10k.petshop.service;


import java.util.Optional;

import com.ku10k.petshop.models.User;
import com.ku10k.petshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) return optionalUser.get();
        else throw new UsernameNotFoundException("User with email " + email + " isn't found");
    }
}

