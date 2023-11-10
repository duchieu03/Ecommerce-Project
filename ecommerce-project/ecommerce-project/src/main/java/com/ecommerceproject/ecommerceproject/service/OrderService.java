package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.CartDTO;
import com.ecommerceproject.ecommerceproject.model.*;
import com.ecommerceproject.ecommerceproject.repository.OrderDetailRepository;
import com.ecommerceproject.ecommerceproject.repository.OrderRepository;
import com.ecommerceproject.ecommerceproject.repository.ProductRepository;
import com.ecommerceproject.ecommerceproject.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockRepository stockRepository;
    @Override
    public void addOrder(List<CartDTO> cartDTOS, User user) {
        Order order = new Order();
        order.setUser(user);
        List<OrderDetail> list = new ArrayList<>();
        order.setOrderDetails(list);
        orderRepository.save(order);
        for(CartDTO cartDTO : cartDTOS){
            OrderDetail orderDetail= new OrderDetail();
            Product product = new Product();
            product.setId(cartDTO.getId());
            //order detail
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cartDTO.getQuantity());
            orderDetail.setOrder(order);
           // orderDetailRepository.save(orderDetail);
            //stock
            Stock stock = stockRepository.findByProduct(product).get();
            stock.setQuantity(stock.getQuantity()-cartDTO.getQuantity());
            stockRepository.save(stock);
            list.add(orderDetail);
        }
        orderRepository.save(order);
    }

//    private Product convertToProduct(CartDTO cartDTO){
//        Product product = new Product();
//        product.set
//    }
}
