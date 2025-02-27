package com.yazdahni.payment.service;

import com.yazdahni.payment.dto.PaymentRequest;
import com.yazdahni.payment.notification.NotificationProducer;
import com.yazdahni.payment.notification.PaymentNotificationRequest;
import com.yazdahni.payment.repository.PaymentRepository;
import com.yazdahni.payment.util.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final NotificationProducer notificationProducer;


    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
