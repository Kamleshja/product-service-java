package com.example.product_service.validation;

import com.example.product_service.dto.CartProductRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartProductsValidator
        implements ConstraintValidator<ValidCartProducts, List<CartProductRequest>> {

    @Override
    public boolean isValid(
            List<CartProductRequest> products,
            ConstraintValidatorContext context) {

        if (products == null || products.isEmpty()) {
            return true;
        }

        if (products.size() > 20) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Cart cannot contain more than 20 products"
            ).addConstraintViolation();

            return false;
        }

        Set<Integer> productIds = new HashSet<>();

        for (CartProductRequest product : products) {

            if (!productIds.add(product.id())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Duplicate product ID found: " + product.id()
                ).addConstraintViolation();

                return false;
            }
        }

        return true;
    }
}