package com.example.product_service.dto;

import jakarta.validation.constraints.Positive;

public record CartProductRequest(

        @Positive(message = "Product ID must be greater than 0")
        int id,

        @Positive(message = "Product quantity must be greater than 0")
        int quantity
) {
}