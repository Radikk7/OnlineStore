package com.example.onlinestore.repository;

import com.example.onlinestore.model.Product;
import com.example.onlinestore.model.ProductInBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInBasketRepository  extends JpaRepository<ProductInBasket,Long> {
        public List<ProductInBasket> findProductInBasketByProduct(Product product);
}
