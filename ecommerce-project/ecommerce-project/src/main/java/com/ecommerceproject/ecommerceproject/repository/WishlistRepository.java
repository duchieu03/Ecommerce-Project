package com.ecommerceproject.ecommerceproject.repository;

import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    public List<Wishlist> findByUser(User user);
    public void deleteByUserAndProduct(User user, Product product);
    public Optional<Wishlist> findByUserAndProduct(User user, Product product);
}
