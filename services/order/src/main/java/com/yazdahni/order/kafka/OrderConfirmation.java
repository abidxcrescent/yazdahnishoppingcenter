package com.yazdahni.order.kafka;

import com.yazdahni.order.dto.CustomerResponse;
import com.yazdahni.order.dto.PurchaseResponse;
import com.yazdahni.order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
