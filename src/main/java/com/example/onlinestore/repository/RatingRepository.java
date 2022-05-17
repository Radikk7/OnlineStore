package com.example.onlinestore.repository;

import com.example.onlinestore.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {

}
