package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.model.Cart;
import com.ecommerceproject.ecommerceproject.model.User;

import java.util.List;

public interface ICartService {
    public List<Cart> findByUser(User user);
    public void addCart(User user, Integer id, Integer quantity);
    public void deleteCart(User user, Integer id);
    public void deleteAllCart(User user);
}
