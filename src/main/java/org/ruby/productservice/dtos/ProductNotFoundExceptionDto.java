package org.ruby.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDto {
    public String message;
    public String resolutionDetails;
    private Long productId;
}
