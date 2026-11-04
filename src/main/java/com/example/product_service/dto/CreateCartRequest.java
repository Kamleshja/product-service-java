package com.example.product_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import com.example.product_service.validation.ValidCartProducts;

import java.util.List;

public record CreateCartRequest(

        @Positive(message = "User ID must be greater than 0")
        int userId,

        @ValidCartProducts
        @NotEmpty(message = "Cart must contain at least one product")
        List<@Valid CartProductRequest> products
) {
}