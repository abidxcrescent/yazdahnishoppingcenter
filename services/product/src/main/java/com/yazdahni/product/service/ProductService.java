package com.yazdahni.product.service;

import com.yazdahni.product.dto.ProductPurchaseRequest;
import com.yazdahni.product.dto.ProductPurchaseResponse;
import com.yazdahni.product.dto.ProductRequest;
import com.yazdahni.product.dto.ProductResponse;
import com.yazdahni.product.exception.ProductPurchaseException;
import com.yazdahni.product.repository.ProductRepository;
import com.yazdahni.product.util.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        return null;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository
                .findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: "+ productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
