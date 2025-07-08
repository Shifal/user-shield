package com.shifal.userapi.controller;

import com.shifal.userapi.dto.UpdateProfileRequest;
import com.shifal.userapi.dto.UserResponse;
import com.shifal.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ((com.shifal.userapi.model.User) userDetails).getId();
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        Long userId = ((com.shifal.userapi.model.User) userDetails).getId();
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }
}