package com.aiart.platform.auth;

import com.aiart.platform.auth.dto.LoginRequest;
import com.aiart.platform.auth.dto.SignupRequest;
import com.aiart.platform.user.User;
import com.aiart.platform.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("EMAIL_ALREADY_EXISTS");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole("USER");

        return userRepository.save(user).getId();
    }

    public boolean login(LoginRequest request) {
        return userRepository.findByEmail(request.email())
                .map(user -> passwordEncoder.matches(request.password(), user.getPasswordHash()))
                .orElse(false);
    }
}