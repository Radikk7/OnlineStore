package com.example.onlinestore.controler;


import com.example.onlinestore.model.Orders;
import com.example.onlinestore.model.Product;
import com.example.onlinestore.model.Rating;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repository.OrdersRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.repository.RatingRepository;
import com.example.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductRating {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RatingRepository ratingRepository;

    @GetMapping("/account")
    public String accountrait(@AuthenticationPrincipal User user, Model model){
      List<Orders> ordersList= ordersRepository.findAllByUser(user);
   //   ordersList.stream().flatMap(x-> x.getProductList().stream()).forEach(x-> System.out.println(x));
   // List<Product> productList= orders.getProductList();
   //    System.out.println(productList);
        model.addAttribute("orders",ordersList);
        return "orders";
    }
    @GetMapping("/rating/{id}")
    public String rating(@PathVariable(name = "id")String id, Model model){
    Product product= productRepository.findById(Long.parseLong(id)).get();
    List<Product>productList = new ArrayList<>();
    productList.add(product);
    model.addAttribute("product",productList);
        return "ratingform";

    }
    @PostMapping("/rating")
    public String graderadting(@AuthenticationPrincipal User user,@RequestParam(name = "id")String id,@RequestParam(name = "name")String name,
                               @RequestParam(name = "rate")String rate){
        Product product = productRepository.findById(Long.parseLong(id)).get();
        product.setName(name);
        List<Rating>ratingList= product.getRatingList();
        Rating rating = new Rating();
        rating.setGrade(Integer.parseInt(rate));
        rating.setUser(user);
        ratingRepository.save(rating);
        ratingList.add(rating);
        product.setRatingList(ratingList);
        productRepository.save(product);
        return "redirect:/account";
    }


}
