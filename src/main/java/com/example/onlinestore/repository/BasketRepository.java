package com.example.onlinestore.repository;

import com.example.onlinestore.model.Basket;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.model.ProductInBasket;
import com.example.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {
        public Basket findByUser(User user);
        public List<Basket> findByProductInBasketListContains(ProductInBasket productInBasketList);

}
