package com.crio.coderhack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserDTO {
    @NotBlank(message = "userId is required")
    @Size(min = 5, message = "userId must have at least 5 characters")
    private String userId;

    @NotBlank(message = "username is required")
    @Size(min = 3, message = "username must have at least 3 characters")
    private String username;

    public CreateUserDTO(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
