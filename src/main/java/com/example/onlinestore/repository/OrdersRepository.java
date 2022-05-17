package com.example.onlinestore.repository;

import com.example.onlinestore.model.Orders;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
        public Orders findByUser(User user);
        public List<Orders> findAllByUser(User user);
        public List<Orders> findByProductListContains(Product product);
}
