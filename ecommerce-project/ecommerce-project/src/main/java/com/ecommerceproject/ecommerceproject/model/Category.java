package com.ecommerceproject.ecommerceproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="category_name",unique = true)
    @NotBlank(message = "Category name must not be blank")
    @NotNull(message = "Category name must not be null")
    private String categoryName;
    @Column(name="description")
    @NotBlank(message = "Description name must not be blank")
    @NotNull(message = "Description name must not be null")
    private String description;
    @Column(name="img_url")
    @NotBlank(message = "Image Url name must not be blank")
    @NotNull(message = "Image Url name must not be null")
    private String imgUrl;
}
