package com.example.onlinestore.controler;


import com.example.onlinestore.model.*;
import com.example.onlinestore.repository.BasketRepository;
import com.example.onlinestore.repository.OrdersRepository;
import com.example.onlinestore.repository.ProductInBasketRepository;
import com.example.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderControler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductInBasketRepository productInBasketRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/orders/buy")
    public String buyorder(@AuthenticationPrincipal User user,Model model){
        Basket basket= basketRepository.findByUser(user);
        List<ProductInBasket>productInBasketList=basket.getProductInBasketList();
        double summ = productInBasketList.stream().mapToDouble(x-> x.getProduct().getPrice().doubleValue()* x.getCount()).sum();
        BigDecimal b = new BigDecimal(summ, MathContext.DECIMAL64);
        model.addAttribute("price",b);
      return "buyorder";
    }
    @PostMapping("/basket/buy")
    public String basketBuy(@AuthenticationPrincipal User user,@RequestParam(name = "Phone")String Phone,
                            @RequestParam(name = "City")String City,
                            @RequestParam(name = "Street")String Street, @RequestParam(name = "House")String House,
                            @RequestParam(name = "CartNumber")String CartNumber, Model model){
        String a = City + " " + Street;
        Orders orders = new Orders();
        orders.setTelephone(Phone);
        orders.setAddress(a);
        orders.setUser(user);
        orders.setPaymant(CartNumber);
        Basket basket= basketRepository.findByUser(user);
          List<ProductInBasket> productInBasketList= basket.getProductInBasketList();
          double summ = productInBasketList.stream().mapToDouble(x-> x.getProduct().getPrice().doubleValue()* x.getCount()).sum();
          List<Product>productList=productInBasketList.stream().map(x-> x.getProduct()).collect(Collectors.toList());
          BigDecimal b = new BigDecimal(summ, MathContext.DECIMAL64);
          orders.setPrice(b);
          orders.setProductList(productList);
        Date date = new Date(new java.util.Date().getTime());
        orders.setDate(date);
          ordersRepository.save(orders);
        model.addAttribute("order",orders);
        return "thanks";
    }

}
