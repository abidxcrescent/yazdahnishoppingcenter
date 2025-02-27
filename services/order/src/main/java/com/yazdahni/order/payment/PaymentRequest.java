package com.yazdahni.order.payment;

import com.yazdahni.order.dto.CustomerResponse;
import com.yazdahni.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
