package com.example.onlinestore.controler;

import com.example.onlinestore.model.ProductManufactory;
import com.example.onlinestore.repository.ProductManufactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProductManufactoryControler {
    @Autowired
    ProductManufactoryRepository productManufactoryRepository;
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/manufactory")
    public String manufactory(Model model){
        List<ProductManufactory>productManufactoryList = new ArrayList<>();
       productManufactoryList= productManufactoryRepository.findAll();
       model.addAttribute("manufactory",productManufactoryList);
        return "productmanufactory";
    }

    @PostMapping("/addmanufactory")
    public String manufactoryadd(@RequestParam(name = "name")String name){
    ProductManufactory productManufactory = new ProductManufactory();
    productManufactory.setName(name);
    Date date = new Date();
    productManufactory.setDate(date);
    productManufactoryRepository.save(productManufactory);
    return "redirect:/manufactory";
    }


}
