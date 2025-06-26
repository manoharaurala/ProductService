package org.ruby.productservice.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    //private List<String> roles = new ArrayList<>();
}
