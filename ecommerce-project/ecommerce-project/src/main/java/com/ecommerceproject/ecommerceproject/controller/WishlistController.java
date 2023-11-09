package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.Wishlist;
import com.ecommerceproject.ecommerceproject.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("http://localhost:8080")
public class WishlistController {
    @Autowired
    public IWishlistService wishlistService;

    @GetMapping
    public ResponseEntity<?> getWishlist(@AuthenticationPrincipal User user){
        if(user!=null){
            List<Wishlist> list = wishlistService.findByUser(user);
            List<Product> products = list.stream().map(Wishlist::getProduct).toList();
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), products, "Successfully");
            return ResponseEntity.ok(responseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, "Log in to view wishlist");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addWishlist(@AuthenticationPrincipal User user, @PathVariable Integer id){
        if(user!=null){
            if(wishlistService.addWishlist(user,id)){
                ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null, "Successfully");
                return ResponseEntity.ok(responseDTO);
            }
            else{
                ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, "Already in wishlist");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
            }
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, "Log in to add to wishlist");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteWishlist(@AuthenticationPrincipal User user, @PathVariable Integer id){
        if(user!=null){
            if(wishlistService.deleteWishlist(user,id)){
                ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null, "Successfully");
                return ResponseEntity.ok(responseDTO);
            }
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, "Interval error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

}
