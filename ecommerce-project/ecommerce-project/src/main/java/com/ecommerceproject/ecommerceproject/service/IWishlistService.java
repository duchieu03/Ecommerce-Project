package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.Wishlist;

import java.util.List;

public interface IWishlistService {
    public List<Wishlist> findByUser(User user);
    public boolean addWishlist(User user, Integer productId);
    public boolean deleteWishlist(User user, Integer productId);
}
