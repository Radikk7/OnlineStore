package com.example.onlinestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;

@Entity
public class ProductManufactory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;


    public ProductManufactory(Long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public ProductManufactory() {
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

    public String getDate() {
        Date date = new Date();
     //   final String OLD_FORMAT = "dd/MM/yyyy HH:mm:ss";
       //final String  NEW_FORMAT = "dd/MM/yyyy";
        String  NEW_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String toString(){
        return getName();
    }
}
