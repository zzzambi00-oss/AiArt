package com.aiart.platform.auth;

import com.aiart.platform.auth.dto.LoginRequest;
import com.aiart.platform.auth.dto.SignupRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody SignupRequest request) {
        Long userId = authService.signup(request);
        return Map.of("success", true, "data", Map.of("userId", userId));
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        boolean ok = authService.login(request);
        if (!ok) {
            return Map.of("success", false, "error", Map.of("code", "INVALID_CREDENTIALS"));
        }
        return Map.of("success", true, "data", Map.of("message", "login_ok"));
    }
}