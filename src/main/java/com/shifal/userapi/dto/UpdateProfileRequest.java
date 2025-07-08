package com.shifal.userapi.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    private String firstName;
    private String lastName;
}