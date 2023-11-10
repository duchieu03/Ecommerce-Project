package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.exception.ProductNotFoundException;
import com.ecommerceproject.ecommerceproject.model.Cart;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.repository.CartRepository;
import com.ecommerceproject.ecommerceproject.repository.ProductRepository;
import com.ecommerceproject.ecommerceproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements  ICartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Cart> findByUser(User user) {

        return cartRepository.findByUser(user);
    }

    @Override
    public void addCart(User user, Integer id,Integer quantity) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product product1 = product.get();
        Optional<Cart> cartOptional = cartRepository.findByUserAndProduct(user,product1);
        if(cartOptional.isEmpty()){
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product.get());
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
        else{
            Cart cart = cartOptional.get();
            cart.setQuantity(cart.getQuantity()+quantity);
            cartRepository.save(cart);
        }

    }

    @Override
    @Transactional
    public void deleteCart(User user, Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product product1 = product.get();
        cartRepository.deleteByUserAndProduct(user,product1);
    }

    @Override
    @Transactional
    public void deleteAllCart(User user) {
        cartRepository.deleteByUser(user);
    }
}
