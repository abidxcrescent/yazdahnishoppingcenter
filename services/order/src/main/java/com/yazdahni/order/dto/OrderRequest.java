package com.yazdahni.order.dto;

import com.yazdahni.order.enums.PaymentMethod;
import com.yazdahni.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,

        String reference,

        @Positive(message = "Oder amount cannot be lower than 0")
        BigDecimal amount,

        @NotNull(message = "Payment method should be valid")
        PaymentMethod paymentMethod,

        @NotEmpty(message = "Customer id cannot be empty")
        @NotBlank(message = "Customer id cannot be blank")
        @NotNull(message = "Customer id cannot be null")
        String customerId,

        @NotEmpty(message = "Should purchase atleast one product")
        List<PurchaseRequest> products
) {
}
