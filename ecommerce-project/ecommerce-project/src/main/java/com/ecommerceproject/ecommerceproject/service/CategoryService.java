package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.exception.CategoryNotFoundException;
import com.ecommerceproject.ecommerceproject.model.Category;
import com.ecommerceproject.ecommerceproject.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, Category category) {
        Optional<Category> cate = categoryRepository.findById(id);
        if(cate.isPresent()){
            Category curCate = cate.get();
            curCate.setCategoryName(category.getCategoryName());
            curCate.setDescription(category.getDescription());
            curCate.setImgUrl(category.getImgUrl());
            categoryRepository.save(curCate);
        }else{
            throw new CategoryNotFoundException();
        }
    }
}
