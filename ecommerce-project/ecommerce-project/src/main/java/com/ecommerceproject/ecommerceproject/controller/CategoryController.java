package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.model.Category;
import com.ecommerceproject.ecommerceproject.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Successful");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListCategory(){
        List<Category> list = categoryService.getAllCategory();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), list,"Successful");
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCategory
            (@Valid @RequestBody Category category
            ,@PathVariable Integer id){
        categoryService.updateCategory(id,category);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Successful");
        return ResponseEntity.ok().body(responseDTO);
    }
}
