package com.yazdahni.order.service;

import com.yazdahni.order.customer.CustomerClient;
import com.yazdahni.order.dto.OrderRequest;
import com.yazdahni.order.dto.OrderResponse;
import com.yazdahni.order.dto.PurchaseRequest;
import com.yazdahni.order.exception.BusinessException;
import com.yazdahni.order.kafka.OrderConfirmation;
import com.yazdahni.order.kafka.OrderProducer;
import com.yazdahni.order.orderline.OrderLineRequest;
import com.yazdahni.order.orderline.OrderLineService;
import com.yazdahni.order.payment.PaymentClient;
import com.yazdahni.order.payment.PaymentRequest;
import com.yazdahni.order.product.ProductClient;
import com.yazdahni.order.repository.OrderRepository;
import com.yazdahni.order.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    private final OrderMapper orderMapper;

    private final PaymentClient paymentClient;

    public Integer createOder(OrderRequest request) {
        // check the customer --> customer-ms using feign client

        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() ->
                        new BusinessException("Cannot create order for customer:: "+request.customerId()));

        // purchase the products --> purchase-ms using RestTemplate

        var purchasedProducts = this.productClient.purchaseProducts(request.products());

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

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send order confirmation --> notification-ms (kafka)

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(this.orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new BusinessException("Cannot find order:: "+orderId));
    }
}
