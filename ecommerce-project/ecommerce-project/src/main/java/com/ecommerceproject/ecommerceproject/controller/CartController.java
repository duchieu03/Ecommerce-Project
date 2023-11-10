package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.CartDTO;
import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.model.Cart;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
@CrossOrigin
public class CartController {
    @Autowired
    ICartService cartService;

    @GetMapping()
    public ResponseEntity<?> getCart(@AuthenticationPrincipal User user){
        if(user==null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(),null,"Log in to add to cart");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
        }
        List<CartDTO> products = cartService.findByUser(user).stream().map(this::convertToCartDTO).toList();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(),products,"Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addCart(@AuthenticationPrincipal User user, @PathVariable Integer id,@RequestParam Integer quantity){
        if(user==null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(),null,"Log in to add to cart");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
        }
        cartService.addCart(user,id,quantity);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(),null,"Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@AuthenticationPrincipal User user, @PathVariable Integer id){
        if(user==null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(),null,"Log in to add to cart");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
        }
        cartService.deleteCart(user,id);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(),null,"Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteAllCart(@AuthenticationPrincipal User user){
        if(user==null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN.value(),null,"Log in to add to cart");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDTO);
        }
        cartService.deleteAllCart(user);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(),null,"Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    private CartDTO convertToCartDTO(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getProduct().getId());
        cartDTO.setName(cart.getProduct().getName());
        cartDTO.setPrice(cart.getProduct().getPrice());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setDescription(cart.getProduct().getDescription());
        cartDTO.setImgUrl(cart.getProduct().getImgUrl());
        return cartDTO;
    }
}
