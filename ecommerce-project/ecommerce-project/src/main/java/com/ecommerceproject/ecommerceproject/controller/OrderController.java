package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.CartDTO;
import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("add")
    public ResponseEntity<?> addOrder(@RequestBody List<CartDTO> cartDTOS, @AuthenticationPrincipal User user){
        if(user==null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(), null,"Log in to add to order");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
        }
        orderService.addOrder(cartDTOS,user);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Add order successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
