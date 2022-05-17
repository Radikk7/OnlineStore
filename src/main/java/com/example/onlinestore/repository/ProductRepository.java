package com.example.onlinestore.repository;

import com.example.onlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findAllByOrderById();
    public List<Product> findAllByOrderByIdDesc();
    public List<Product> findAllByOrderByPrice();
    public List<Product> findAllByOrderByPriceDesc();

   // public Product findProductByNameAndDescription(String productname);
    //public Product findProductByIdAndDescription(Long id);
}
