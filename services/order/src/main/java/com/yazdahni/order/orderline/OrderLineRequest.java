package com.yazdahni.order.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "Invalid product")
        Integer productId,
        @Positive(message = "Quantity cannot be lowe than zero")
        double quantity
) {
}
