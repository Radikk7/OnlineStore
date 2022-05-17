package com.example.onlinestore.model;

import javax.persistence.*;

@Entity
public class ProductInBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private int count;

    public ProductInBasket(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public ProductInBasket() {
    }

    public ProductInBasket(Long id, Product product, int count) {
        this.id = id;
        this.product = product;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
