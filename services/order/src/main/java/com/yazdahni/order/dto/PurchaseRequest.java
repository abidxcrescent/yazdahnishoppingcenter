package com.yazdahni.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "Invalid product")
        Integer productId,

        @Positive(message = "Quantity cannot be lowe than zero")
        double quantity
) {
}
