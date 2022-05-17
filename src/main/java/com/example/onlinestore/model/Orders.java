package com.example.onlinestore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private String telephone;
    private String address;
    private String paymant;
    private Date date;
    private BigDecimal price;
    @ManyToMany
    private List<Product> productList;

    public Orders() {
    }

    public Orders(Long id, User user, String telephone, String address, String paymant, Date date, BigDecimal price) {
        this.id = id;
        this.user = user;
        this.telephone = telephone;
        this.address = address;
        this.paymant = paymant;
        this.date = date;
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Orders(Long id, User user, String telephone, String address, String paymant, Date date) {
        this.id = id;
        this.user = user;
        this.telephone = telephone;
        this.address = address;
        this.paymant = paymant;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymant() {
        return paymant;
    }

    public void setPaymant(String paymant) {
        this.paymant = paymant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
