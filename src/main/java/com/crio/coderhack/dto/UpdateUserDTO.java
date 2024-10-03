package com.crio.coderhack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateUserDTO {

    @NotNull(message = "Score is required.")
    @Min(value = 0, message = "Score should not be less than 0")
    @Max(value = 100, message = "Score should not be greater than 100")
    private Integer score;

    public UpdateUserDTO() {}

    public UpdateUserDTO(Integer score) {
        this.score = score;
    }
}
