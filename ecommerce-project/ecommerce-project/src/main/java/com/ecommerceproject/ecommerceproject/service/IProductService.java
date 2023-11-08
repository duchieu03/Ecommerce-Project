package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.ecommerceproject.model.Product;

import java.util.List;

public interface IProductService {
    public ProductDTO createProduct(ProductDTO productDTO);
    public List<ProductDTO> getAllProduct();
    public void updateProduct(Integer id, ProductDTO productDTO);
}
