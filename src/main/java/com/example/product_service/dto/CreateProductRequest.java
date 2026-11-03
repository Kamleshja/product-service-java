package com.example.product_service.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateProductRequest(
        @NotBlank(message = "Product title is required")
        String title,
        String description,
        Double price,
        String category
) {
}
