package com.example.product_service.dto;

public record CartProductResponse(
        int id,
        String title,
        double price,
        int quantity,
        double total,
        double discountPercentage,
        Double discountedTotal,
        String thumbnail
) {
}