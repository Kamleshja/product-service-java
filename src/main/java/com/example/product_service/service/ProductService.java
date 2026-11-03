package com.example.product_service.service;

import com.example.product_service.client.DummyJsonClient;
import com.example.product_service.dto.Product;
import com.example.product_service.dto.ProductPageResponse;
import com.example.product_service.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final DummyJsonClient dummyJsonClient;

    public ProductService(DummyJsonClient dummyJsonClient) {
        this.dummyJsonClient = dummyJsonClient;
    }

    public ProductPageResponse getAllProducts(
            int page,
            int size,
            List<String> sort,
            String category,
            String brand) {

        // Fetch all products from DummyJSON
        List<Product> products = dummyJsonClient
                .getAllProducts()
                .products();

        // Filter- brand and category
        if (category != null && !category.isBlank()) {
            products = products.stream()
                    .filter(product ->
                            product.category().equalsIgnoreCase(category))
                    .toList();
        }

        if (brand != null && !brand.isBlank()) {
            products = products.stream()
                    .filter(product ->
                            product.brand() != null &&
                                    product.brand().equalsIgnoreCase(brand))
                    .toList();
        }

        // Sorting
        Comparator<Product> finalComparator = null;
        for (String sortCondition : sort) {
            String[] sortParams = sortCondition.split(",");

            if (sortParams.length != 2) {
                throw new IllegalArgumentException(
                        "Sort format must be field,direction");
            }

            String sortField = sortParams[0].trim();
            String direction = sortParams[1].trim();

            if (!direction.equalsIgnoreCase("asc")
                    && !direction.equalsIgnoreCase("desc")) {
                throw new IllegalArgumentException(
                        "Sort direction must be asc or desc");
            }

            Comparator<Product> currentComparator =
                    getComparator(sortField, direction);

            if (finalComparator == null) {
                finalComparator = currentComparator;
            } else {
                finalComparator = finalComparator.thenComparing(currentComparator);
            }
        }

        if (finalComparator != null) {
            products = products.stream()
                    .sorted(finalComparator)
                    .toList();
        }

        long totalElements = products.size();

        int startIndex = page * size;

        int endIndex = Math.min(startIndex + size, products.size());

        List<Product> paginatedProducts =
                products.subList(startIndex, endIndex);

        // Calculate total pages
        int totalPages = (int) Math.ceil((double) totalElements / size);

        // Check whether next page exists
        boolean hasNext = page < totalPages - 1;

        return new ProductPageResponse(
                paginatedProducts,
                page,
                size,
                totalElements,
                totalPages,
                hasNext
        );
    }

    private Comparator<Product> getComparator(String sortField, String direction) {

        Comparator<Product> comparator = switch (sortField) {
            case "price" -> Comparator.comparing(Product::price);
            case "rating" -> Comparator.comparing(Product::rating);
            case "title" -> Comparator.comparing(Product::title);
            case "stock" -> Comparator.comparing(Product::stock);
            default -> throw new IllegalArgumentException(
                    "Unsupported sorting field: " + sortField);
        };

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }
}
