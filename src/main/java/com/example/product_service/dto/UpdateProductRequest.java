package com.example.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProductRequest(

        @NotBlank(message = "Product title is required")
        String title,

        @NotBlank(message = "Product description is required")
        String description,

        @NotNull(message = "Product price is required")
        @PositiveOrZero(message = "Product price cannot be negative")
        Double price,

        @NotBlank(message = "Product category is required")
        String category

) {
}