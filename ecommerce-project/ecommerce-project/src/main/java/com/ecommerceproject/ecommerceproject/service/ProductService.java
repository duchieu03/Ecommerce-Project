package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.ecommerceproject.exception.CategoryNotFoundException;
import com.ecommerceproject.ecommerceproject.exception.ProductNotFoundException;
import com.ecommerceproject.ecommerceproject.model.Category;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.repository.CategoryRepository;
import com.ecommerceproject.ecommerceproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
      Category category = categoryRepository
                .findById(productDTO.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());

        productRepository.save(product);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertProductToProductDTO).toList();
    }

    @Override
    public void updateProduct(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }

    private ProductDTO convertProductToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }

}
