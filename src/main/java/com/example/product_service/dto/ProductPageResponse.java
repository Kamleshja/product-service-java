package com.example.product_service.dto;

import java.util.List;

public record ProductPageResponse(
        List<Product> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
}
