package com.shifal.userapi.config;

import com.shifal.userapi.model.Role;
import com.shifal.userapi.model.User;
import com.shifal.userapi.enums.RoleType;
import com.shifal.userapi.repository.RoleRepository;
import com.shifal.userapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartupAdminSeeder {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedAdminUserIfNotExists() {
        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleType.ROLE_ADMIN).build()));

        boolean adminExists = userRepository.findAll().stream()
                .anyMatch(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getName() == RoleType.ROLE_ADMIN));

        if (!adminExists) {
            User admin = User.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .firstName("System")
                    .lastName("Admin")
                    .roles(Set.of(adminRole))
                    .build();
            userRepository.save(admin);
            System.out.println("ADMIN user created successfully!!");
        } else {
            System.out.println("ADMIN already exists. Skipping admin creation.");
        }
    }
}
