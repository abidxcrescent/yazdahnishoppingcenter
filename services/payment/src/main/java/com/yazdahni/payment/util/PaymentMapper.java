package com.yazdahni.payment.util;

import com.yazdahni.payment.dto.PaymentRequest;
import com.yazdahni.payment.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest request) {

        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();

    }
}
