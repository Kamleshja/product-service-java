package com.example.product_service.dto;

import java.util.List;

public record ProductResponse(
        List<Product> products,
        Integer total,
        Integer skip,
        Integer limit
) {
}
