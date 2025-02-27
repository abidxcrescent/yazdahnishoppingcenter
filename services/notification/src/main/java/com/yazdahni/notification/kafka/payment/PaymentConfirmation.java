package com.yazdahni.notification.kafka.payment;

import com.yazdahni.notification.kafka.order.Customer;
import com.yazdahni.notification.kafka.order.Product;

import java.math.BigDecimal;
import java.util.List;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail

) {
}
