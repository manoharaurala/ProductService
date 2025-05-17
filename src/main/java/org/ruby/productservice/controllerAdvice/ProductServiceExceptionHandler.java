package org.ruby.productservice.controllerAdvice;

import org.ruby.productservice.dtos.ExceptionDto;
import org.ruby.productservice.dtos.ProductNotFoundExceptionDto;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException() {
        // Handle the exception
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong!");
        exceptionDto.setResolutionDetails("You need to pay more money to get it resolved from us. Thanks!");
        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
        // Handle the exception
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
        productNotFoundExceptionDto.setProductId(exception.getProductId());
        productNotFoundExceptionDto.setMessage("Product not found!");
        productNotFoundExceptionDto.setResolutionDetails("Please try again with a valid product id");
        return new ResponseEntity<>(
                productNotFoundExceptionDto,
                HttpStatus.NOT_FOUND
        );
    }
}
