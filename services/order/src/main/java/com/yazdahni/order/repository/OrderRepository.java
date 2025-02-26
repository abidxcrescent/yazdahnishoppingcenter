package com.yazdahni.order.repository;

import com.yazdahni.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
