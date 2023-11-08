package com.ecommerceproject.ecommerceproject.repository;

import com.ecommerceproject.ecommerceproject.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
}
