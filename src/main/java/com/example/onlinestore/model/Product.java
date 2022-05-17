package com.example.onlinestore.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal price;


    @Column(length = 5000)
    @Type(type = "text")
    private String description;
    @OneToOne
    private ProductType productType;
    @OneToOne
    private ProductManufactory productManufactory;
    @OneToMany
    private List<Rating> ratingList;
    private String filename;

    public Product() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Product(Long id, String name, BigDecimal price, String description, ProductType productType,
                   ProductManufactory productManufactory, List<Rating> ratingList, String filename) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.productManufactory = productManufactory;
        this.ratingList = ratingList;
        this.filename = filename;
    }

    public Product(Long id, String name, BigDecimal price, String description, ProductType productType,
                   ProductManufactory productManufactory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.productManufactory = productManufactory;

    }

    public Product(Long id, String name, BigDecimal price, String description, ProductType productType, ProductManufactory productManufactory, List<Rating> ratingList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.productManufactory = productManufactory;
        this.ratingList = ratingList;
    }
    public double getValue(){
        if (ratingList.size()==0){
            return 0;
        }
      return ratingList.stream().mapToDouble(x-> x.getGrade()).average().getAsDouble();
    }
    public int getCount(){
        return ratingList.size();
    }

    public Product(Long id, String name, BigDecimal price, String description, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductManufactory getProductManufactory() {
        return productManufactory;
    }

    public void setProductManufactory(ProductManufactory productManufactory) {
        this.productManufactory = productManufactory;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public String toString(){
        return getName() + " " + getPrice() + " " + getDescription() + " "
                + getProductType() + " " + getProductManufactory() + " " + getId() + " " + ratingList;
    }
}
