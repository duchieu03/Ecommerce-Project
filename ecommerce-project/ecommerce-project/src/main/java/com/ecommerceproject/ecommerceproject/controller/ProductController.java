package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        ProductDTO productDTO1= productService.createProduct(productDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), productDTO1,"Successful");
        return  ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("list")
    public  ResponseEntity<ResponseDTO> getAllProduct(){
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), productService.getAllProduct(),"successful");
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Integer id,@Valid @RequestBody ProductDTO productDTO){
        productService.updateProduct(id,productDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"successful");
        return ResponseEntity.ok(responseDTO);
    }
}
