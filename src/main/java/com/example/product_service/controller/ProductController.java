package com.example.product_service.controller;

import com.example.product_service.dto.CreateProductRequest;
import com.example.product_service.dto.CreateProductResponse;
import com.example.product_service.dto.Product;
import com.example.product_service.dto.ProductPageResponse;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number should start from 0") int page,
            @RequestParam(defaultValue = "10")
                @Min(value = 1, message = "Size must be greater than or equal to 1")
                @Max(value = 30, message = "Size must be less than or equal to 30")
            int size,
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand){

        List<String> sort = params.get("sort");

        if (sort == null || sort.isEmpty()) {
            sort = List.of("price,asc");
        }

        return productService.getAllProducts(page, size, sort, category, brand);
    }

    @PostMapping
    public CreateProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }
}