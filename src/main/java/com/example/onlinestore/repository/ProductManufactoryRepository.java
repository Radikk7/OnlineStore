package com.example.onlinestore.repository;

import com.example.onlinestore.model.ProductManufactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductManufactoryRepository extends JpaRepository<ProductManufactory,Long> {
    public ProductManufactory findProductManufactoryByName(String name);
}
