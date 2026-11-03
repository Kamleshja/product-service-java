package com.example.product_service.client;

import com.example.product_service.dto.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DummyJsonClient {

    private final RestClient restClient;

    public DummyJsonClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ProductResponse getAllProducts() {

        return restClient.get()
                .uri("/products")
                .retrieve()
                .body(ProductResponse.class);
    }
}