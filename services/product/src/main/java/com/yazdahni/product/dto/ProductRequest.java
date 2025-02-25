package com.yazdahni.product.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "name cannot be empty")
        String name,

        @NotNull(message = "description cannot be empty")
        String description,

        @Positive(message = "quantity should be positive")
        double availableQuantity,

        @Positive(message = "price should be positive")
        BigDecimal price,

        @NotNull(message = "category id cannot be empty")
        Integer categoryId
) {
}
