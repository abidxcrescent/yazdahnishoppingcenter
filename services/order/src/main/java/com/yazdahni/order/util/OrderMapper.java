package com.yazdahni.order.util;

import com.yazdahni.order.dto.OrderRequest;
import com.yazdahni.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

}
