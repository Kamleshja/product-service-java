package com.example.product_service.controller;

import com.example.product_service.dto.CartResponse;
import com.example.product_service.dto.CreateCartRequest;
import com.example.product_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse createCart(
            @Valid @RequestBody CreateCartRequest request) {

        return cartService.createCart(request);
    }
}