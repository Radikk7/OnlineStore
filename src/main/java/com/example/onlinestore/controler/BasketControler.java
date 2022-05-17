package com.example.onlinestore.controler;


import com.example.onlinestore.model.*;
import com.example.onlinestore.repository.BasketRepository;
import com.example.onlinestore.repository.ProductInBasketRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class BasketControler {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductInBasketRepository productInBasketRepository;
    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/allproduct/addtobasket/{id}")
    public String basket(@AuthenticationPrincipal User user, @PathVariable(name = "id")Long id, Model model){
        Product product= productRepository.findById(id).get();
        Basket basket= basketRepository.findByUser(user);

        if (basket == null){
            basket=new Basket();
            basket.setUser(user);
            ProductInBasket productInBasket = new ProductInBasket();
            productInBasket.setProduct(product);
            productInBasket.setCount(1);
            productInBasketRepository.save(productInBasket);
            basket.setProductInBasketList(List.of(productInBasket));
        }
        else  if (basket.getProductInBasketList().stream().map(ProductInBasket::getProduct).
                anyMatch(x-> x.getName().equals(product.getName()))){
            ProductInBasket productInBasket = basket.getProductInBasketList().stream().
                    filter(x-> x.getProduct().getName().equals(product.getName())).findFirst().get();
            productInBasket.setCount(productInBasket.getCount()+1);
            basket.getProductInBasketList().set(basket.getProductInBasketList().indexOf(productInBasket),productInBasket);
            basketRepository.save(basket);
            return "redirect:/allproduct";

        }

        else {
            ProductInBasket productInBasket = new ProductInBasket();
            productInBasket.setProduct(product);
            productInBasket.setCount(1);
            productInBasketRepository.save(productInBasket);
            basket.getProductInBasketList().add(productInBasket);
                    }

        basketRepository.save(basket);
        return "redirect:/allproduct";
    }
    @GetMapping("/allproduct/basket")
    public String allbasket(@AuthenticationPrincipal User user,Model model){
        Basket basket= basketRepository.findByUser(user);
        List<ProductInBasket>productInBasketList= basket.getProductInBasketList();
        model.addAttribute("prod",productInBasketList);
        double a = productInBasketList.stream().mapToDouble(x-> x.getProduct().getPrice().doubleValue()* x.getCount()).sum();
        model.addAttribute("price",a);
        return "/basket";
    }
   @GetMapping("/allproduct/basket/{id}")
    public String deleteproduct(@AuthenticationPrincipal User user, @PathVariable(name = "id") Long id){
        Basket basket= basketRepository.findByUser(user);
             List<ProductInBasket> productList= basket.getProductInBasketList();
       ProductInBasket productInBasket= productInBasketRepository.findById(id).get();
       productList.remove(productInBasket);
       basket.setProductInBasketList(productList);
       basketRepository.save(basket);
       productInBasketRepository.delete(productInBasket);
       return "redirect:/allproduct/basket";
   }


}
