package com.yazdahni.order.service;

import com.yazdahni.order.customer.CustomerClient;
import com.yazdahni.order.dto.OrderRequest;
import com.yazdahni.order.exception.BusinessException;
import com.yazdahni.order.product.ProductClient;
import com.yazdahni.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;


    public Integer createOder(OrderRequest request) {
        // check the customer --> customer-ms using feign client

        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order for customer:: "+request.customerId()));

        // purchase the products --> purchase-ms using RestTemplate



        // persist order

        // persist order lines

        // start payment process

        // send order confirmation --> notification-ms
        return null;
    }
}
