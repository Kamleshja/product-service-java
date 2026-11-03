package com.example.product_service.dto;

public record Product(
        Integer id,
        String title,
        String description,
        String category,
        String brand,
        Double price,
        Double rating,
        Integer stock,
        String thumbnail
) {
}
