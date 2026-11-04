package com.example.product_service.service;

import com.example.product_service.dto.CartProductRequest;
import com.example.product_service.dto.CartResponse;
import com.example.product_service.dto.CreateCartRequest;
import com.example.product_service.client.DummyJsonClient;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final DummyJsonClient dummyJsonClient;

    public CartService(DummyJsonClient dummyJsonClient) {
        this.dummyJsonClient = dummyJsonClient;
    }

    public CartResponse createCart(CreateCartRequest request) {
        validateCartProducts(request);
        return dummyJsonClient.createCart(request);
    }

    private void validateCartProducts(CreateCartRequest request) {
        for (CartProductRequest product : request.products()) {
            dummyJsonClient.getProductById(product.id());
        }
    }
}