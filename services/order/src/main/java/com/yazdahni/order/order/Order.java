package com.yazdahni.order.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private String customerId;
    private List<OrderLine> orderLines;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
