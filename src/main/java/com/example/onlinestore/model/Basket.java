package com.example.onlinestore.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private  List<ProductInBasket> productInBasketList;
    @OneToOne
    private User user;

    public Basket() {
    }

    public Basket(Long id, List<ProductInBasket> productInBasketList, User user) {
        this.id = id;
        this.productInBasketList = productInBasketList;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInBasket> getProductInBasketList() {
        return productInBasketList;
    }

    public void setProductInBasketList(List<ProductInBasket> productInBasketList) {
        this.productInBasketList = productInBasketList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
