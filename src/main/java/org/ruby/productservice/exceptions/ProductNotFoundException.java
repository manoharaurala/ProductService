package org.ruby.productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final Long productId;

    public ProductNotFoundException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }

    public ProductNotFoundException(Long productId, String message, Throwable cause) {
        super(message, cause);
        this.productId = productId;
    }
}
