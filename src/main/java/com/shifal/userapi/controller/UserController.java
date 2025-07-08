package com.shifal.userapi.controller;

import com.shifal.userapi.dto.UpdateProfileRequest;
import com.shifal.userapi.dto.UserResponse;
import com.shifal.userapi.repository.UserRepository;
import com.shifal.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserById(
            @PathVariable Long id,
            @AuthenticationPrincipal com.shifal.userapi.model.User currentUser
    ) {
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(403).body("Only admins can delete users");
        }

        if (currentUser.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Admin cannot delete their own account");
        }

        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok("User deleted successfully");
                })
                .orElse(ResponseEntity.status(404).body("User not found"));
    }

}