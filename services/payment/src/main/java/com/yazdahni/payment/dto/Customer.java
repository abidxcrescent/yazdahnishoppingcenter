package com.yazdahni.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,

        @NotNull(message = "FirstName is required")
        String firstName,

        @NotNull(message = "LastName is required")
        String lastName,

        @Email(message = "Invalid email")
        @NotNull(message = "Email is required")
        String email
) {

}
