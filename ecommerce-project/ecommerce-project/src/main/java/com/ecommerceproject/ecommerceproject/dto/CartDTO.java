package com.ecommerceproject.ecommerceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Integer id;
    private  String name;
    private String imgUrl;
    private   Double price;
    private String description;
    private Integer quantity;
}
