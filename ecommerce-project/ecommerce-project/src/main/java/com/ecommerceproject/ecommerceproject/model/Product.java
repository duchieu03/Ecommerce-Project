package com.ecommerceproject.ecommerceproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String imgUrl;
    @NotNull
    private Double price;

    private String description;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private Stock stock;
}
