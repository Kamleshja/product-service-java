package com.example.product_service.validation;

import com.example.product_service.dto.CartProductRequest;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CartProductsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCartProducts {

    String message() default "Invalid cart products";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}