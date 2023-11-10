package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.CartDTO;
import com.ecommerceproject.ecommerceproject.model.User;

import java.util.List;

public interface IOrderService {
    public void addOrder(List<CartDTO> cartDTOS, User user);
}
