package com.ecommerceproject.ecommerceproject.repository;

import com.ecommerceproject.ecommerceproject.model.Cart;
import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    public List<Cart> findByUser(User user);
    public Optional<Cart> findByUserAndProduct(User user, Product product);
    public void deleteByUser(User user);
    public void deleteByUserAndProduct(User user, Product product);
}
