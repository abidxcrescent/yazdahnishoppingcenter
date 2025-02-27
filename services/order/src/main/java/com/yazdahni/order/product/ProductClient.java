package com.yazdahni.order.product;

import com.yazdahni.order.dto.PurchaseRequest;
import com.yazdahni.order.dto.PurchaseResponse;
import com.yazdahni.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests){


        HttpHeaders headers = new HttpHeaders();
        // More headers can be added
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requests, headers);

        // Automatically parse the response recieved to PurchaseResponse
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate
                .exchange(productUrl+"/purchase", HttpMethod.POST, requestEntity, responseType);

        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occurred while purchasing products:: "+ responseEntity.getStatusCode());
        }

        return responseEntity.getBody();

    }

}