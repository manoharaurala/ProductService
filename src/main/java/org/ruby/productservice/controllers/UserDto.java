package org.ruby.productservice.controllers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
  private Long id;
  private String name;
  private String email;
  //private List<String> roles = new ArrayList<>();
}
