package com.ecommerceproject.ecommerceproject.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    @NotNull
    @Size(max=32,min=3)
    private String firstName;
    @NotNull
    @Size(max=32,min=3)
    private String lastName;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Size(max=32,min=6)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
    private String password;
}
