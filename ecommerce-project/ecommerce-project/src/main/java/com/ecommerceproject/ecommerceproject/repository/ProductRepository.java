package com.ecommerceproject.ecommerceproject.repository;

import com.ecommerceproject.ecommerceproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
