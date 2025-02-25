package com.yazdahni.product.repository;

import com.yazdahni.product.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByAllIdInOrderById(List<Integer> productIds);

}
