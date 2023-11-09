package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.Wishlist;
import com.ecommerceproject.ecommerceproject.repository.ProductRepository;
import com.ecommerceproject.ecommerceproject.repository.UserRepository;
import com.ecommerceproject.ecommerceproject.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService implements IWishlistService{
    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Wishlist> findByUser(User user)
    {
        User user1 = userRepository.findById(user.getId()).get();
        return user1.getWishlists();
    }

    @Override
    @Transactional
    public boolean addWishlist(User user, Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) return false;
        Product product = productOptional.get();
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByUserAndProduct(user,product);
        if(optionalWishlist.isPresent()){
            System.out.println(optionalWishlist.get().getId());
            return false;
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteWishlist(User user, Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) return false;
        Product product = productOptional.get();
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByUserAndProduct(user,product);
        if(optionalWishlist.isPresent()){
            wishlistRepository.deleteByUserAndProduct(user,product);
        }
        return true;
    }
}
