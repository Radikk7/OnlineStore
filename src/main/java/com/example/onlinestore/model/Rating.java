package com.example.onlinestore.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private int grade;

    public Rating(Long id, User user, int grade) {
        this.id = id;
        this.user = user;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Rating(Long id, User user, Product product) {
        this.id = id;
        this.user = user;

    }

    public Rating() {
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


}
