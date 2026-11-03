package com.example.product_service.dto;

public record CreateProductResponse(
        int id,
        String title,
        String description,
        String category,
        Double price
) {
}