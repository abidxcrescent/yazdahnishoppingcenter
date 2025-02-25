package com.yazdahni.product.repository;

import com.yazdahni.product.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
