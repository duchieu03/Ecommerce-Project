package com.ecommerceproject.ecommerceproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private @NotNull @NotBlank String name;
    private @NotNull @NotBlank String imgUrl;
    private @NotNull  Double price;
    private @NotNull @NotBlank String description;
    private @NotNull Integer categoryId;
}
