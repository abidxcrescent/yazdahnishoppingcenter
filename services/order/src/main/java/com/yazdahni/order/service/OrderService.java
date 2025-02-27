package com.yazdahni.order.service;

import com.yazdahni.order.customer.CustomerClient;
import com.yazdahni.order.dto.OrderRequest;
import com.yazdahni.order.dto.PurchaseRequest;
import com.yazdahni.order.exception.BusinessException;
import com.yazdahni.order.orderline.OrderLineRequest;
import com.yazdahni.order.orderline.OrderLineService;
import com.yazdahni.order.product.ProductClient;
import com.yazdahni.order.repository.OrderRepository;
import com.yazdahni.order.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    public Integer createOder(OrderRequest request) {
        // check the customer --> customer-ms using feign client

        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() ->
                        new BusinessException("Cannot create order for customer:: "+request.customerId()));

        // purchase the products --> purchase-ms using RestTemplate

        this.productClient.purchaseProducts(request.products());

        // persist order

        var order = this.orderRepository.save(mapper.toOrder(request));

        // persist order lines

        for(PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }



        // todo start payment process

        // send order confirmation --> notification-ms (kafka)



        return null;
    }
}
