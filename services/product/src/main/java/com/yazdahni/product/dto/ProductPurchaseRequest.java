package com.yazdahni.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(

        @NotNull(message = "Product id cannot be empty")
        Integer productId,

        @Positive(message = "Quantity cannot be lower than 0")
        double quantity
) {
}
