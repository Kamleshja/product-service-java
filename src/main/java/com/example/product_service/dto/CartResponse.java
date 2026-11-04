package com.example.product_service.dto;

import java.util.List;

public record CartResponse(
        int id,
        List<CartProductResponse> products,
        double total,
        double discountedTotal,
        int userId,
        int totalProducts,
        int totalQuantity
) {
}