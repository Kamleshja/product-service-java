package com.example.product_service.controller;

import com.example.product_service.dto.Product;
import com.example.product_service.dto.ProductPageResponse;
import com.example.product_service.service.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductPageResponse getAllProducts(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(30) int size,
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand){

        List<String> sort = params.get("sort");

        if (sort == null || sort.isEmpty()) {
            sort = List.of("price,asc");
        }

        return productService.getAllProducts(page, size, sort, category, brand);
    }
}