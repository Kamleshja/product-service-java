package com.example.product_service.client;

import com.example.product_service.dto.CreateProductRequest;
import com.example.product_service.dto.Product;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.dto.UpdateProductRequest;
import com.example.product_service.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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

    public Product createProduct(CreateProductRequest request) {
        return restClient.post()
                .uri("/products/add")
                .body(request)
                .retrieve()
                .body(Product.class);
    }

    public Product getProductById(int id) {
        try {
            return restClient.get()
                    .uri("/products/{id}", id)
                    .retrieve()
                    .body(Product.class);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new ProductNotFoundException(
                    "Product not found with ID: " + id);
        }
    }

    public Product updateProduct(int id, UpdateProductRequest request) {
        return restClient.put()
                .uri("/products/{id}", id)
                .body(request)
                .retrieve()
                .body(Product.class);
    }
}