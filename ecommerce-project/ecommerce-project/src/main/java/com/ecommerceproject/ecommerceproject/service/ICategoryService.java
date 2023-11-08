package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.model.Category;

import java.util.List;

public interface ICategoryService {
    public void createCategory(Category category);
    public List<Category> getAllCategory();
    public void updateCategory(Integer id, Category category);
}
