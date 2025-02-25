package com.yazdahni.product.service;

import com.yazdahni.product.dto.ProductPurchaseRequest;
import com.yazdahni.product.dto.ProductPurchaseResponse;
import com.yazdahni.product.dto.ProductRequest;
import com.yazdahni.product.dto.ProductResponse;
import com.yazdahni.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Integer createProduct(@Valid ProductRequest request) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProducts(@Valid List<ProductPurchaseRequest> request) {
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
