package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.model.Product;
import com.ecommerceproject.ecommerceproject.model.Stock;
import com.ecommerceproject.ecommerceproject.repository.ProductRepository;
import com.ecommerceproject.ecommerceproject.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService implements IStockService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;
    @Override
    public void updateStock(Integer id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) return;
        Optional<Stock> stockOptional = stockRepository.findByProduct(productOptional.get());
        if(stockOptional.isPresent()){
            Stock stock = stockOptional.get();
            stock.setQuantity(quantity);
            stockRepository.save(stock);
        }
    }
}
