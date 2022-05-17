package com.example.onlinestore.controler;

import com.example.onlinestore.model.*;
import com.example.onlinestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ProductControler {
    @Value("${upload.path}")
    private String upload;
    private String sortTypeId ;
    private String sortTypePrice;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductInBasketRepository productInBasketRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductManufactoryRepository productManufactoryRepository;
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/product")
    public String product(Model model){
        List<ProductManufactory>productManufactoryList=new ArrayList<>();
        productManufactoryList=productManufactoryRepository.findAll();
     model.addAttribute("listmanufactory",productManufactoryList);
        return "newproduct";
    }

  @PostMapping("/addproduct")
  public String productadd(@RequestParam(name ="productname")String productname, @RequestParam(name = "price") BigDecimal price,
                           @RequestParam(name = "description")String description, @RequestParam(name = "productmanufactory")
                           String productmanufactor, @RequestParam(name = "file")MultipartFile multipartFile) throws IOException {
      Product product = new Product();
      product.setName(productname);
      product.setPrice(price);
      product.setDescription(description);

      String yyIdFile = UUID.randomUUID().toString();
      String resultFileName =yyIdFile + "." + multipartFile.getOriginalFilename();
      product.setFilename(resultFileName);// сохраняем имя файла в базе
      multipartFile.transferTo(new File(upload + "/" + resultFileName));// сохраняет все на комп
      ProductManufactory productManufactory = new ProductManufactory();
      productManufactory= productManufactoryRepository.findProductManufactoryByName(productmanufactor);
      product.setProductManufactory(productManufactory);
     // product.setRating(rating); та же история что выше
      productRepository.save(product);
      return "redirect:/allproduct";
  }
    @GetMapping("/allproduct")
    public String allproduct(@AuthenticationPrincipal User user, @RequestParam(name = "sort",required = false)String sort,
                             @RequestParam(name = "fild",required = false)String fild,
                             @RequestParam(name = "page",required= false)String page, Model model){
        List<Product>productList = new ArrayList<>();

        if (sort == null || fild ==null ){
           List<Product>productList3 = productRepository.findAll();

       productList= productList3.stream().filter(x-> x.getRatingList().size() > 0).sorted((x,y) -> (int) (x.getRatingList().stream().mapToDouble(Rating::getGrade).average().
                  getAsDouble()-y.getRatingList().stream().mapToDouble(Rating::getGrade).average().getAsDouble())).collect(Collectors.toList());
       List<Product>productList1 = productList3.stream().filter(x-> x.getRatingList().size() ==0).collect(Collectors.toList());
        productList.addAll(productList1);

        }
        else if (sort.equals("up")& fild.equals("id")){
            productList = productRepository.findAllByOrderById();
            sortTypeId = "up";
            sortTypePrice = "";
        }
        else if (sort.equals("down") & fild.equals("id")){
            productList = productRepository.findAllByOrderByIdDesc();
            sortTypeId = "down";
            sortTypePrice = "";
        }
        else if (sort.equals("up") & fild.equals("price")){
            productList= productRepository.findAllByOrderByPrice();
            sortTypePrice = "up";
            sortTypeId = "";
        }
        else if(sort.equals("down") & fild.equals("price")){
            productList = productRepository.findAllByOrderByPriceDesc();
            sortTypePrice = "down";
            sortTypeId = "";
        }

        if(page!=null){
            if(sortTypeId=="up"){
                productList = productRepository.findAllByOrderById();
            }
            else if (sortTypeId=="down"){
                productList = productRepository.findAllByOrderByIdDesc();
            }
            else if(sortTypePrice=="up"){
                productList= productRepository.findAllByOrderByPrice();
            }
            else if (sortTypePrice=="down"){
                productList = productRepository.findAllByOrderByPriceDesc();
            }
            int b = Integer.parseInt(page) * 10 -10;
           productList=productList.stream().skip(b).limit(10).collect(Collectors.toList());
        }

        model.addAttribute("username",user.getUsername());
        model.addAttribute("Product",productList);
        return "allproducts1";
    }
    @GetMapping("/description/about/{id}")
    public String productdescription(@PathVariable(name = "id")String id,Model model){
    List<Product>productList = new ArrayList<>();
    Product product = new Product();
    //product= productRepository.findById(Long.parseLong(id)).get();
    product= productRepository.findById((Long.parseLong(id))).get();
    productList.add(product);
    model.addAttribute("product",productList);
    return "about";
    }
    @GetMapping("/adminallproduct")
    public String adminAllProducts(@AuthenticationPrincipal User user,Model model){
        List<Product>productList = new ArrayList<>();
        productList= productRepository.findAll();
        model.addAttribute("adminname",user.getUsername());
        model.addAttribute("allproducts",productList);
        return "adminallproducts";
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/adminallproduct/{id}")
    public String deleteproduct(@PathVariable(name = "id")Long id){
        Product product = new Product();
        product= productRepository.findById(id).get();
        List<Orders>ordersList=ordersRepository.findByProductListContains(product);
        for (int i = 0; i < ordersList.size(); i++){
            ordersList.get(i).getProductList().remove(product);
        }
        ordersRepository.saveAll(ordersList);
        List<ProductInBasket>productInBasketList= productInBasketRepository.findProductInBasketByProduct(product);
           List<Basket>basketList= basketRepository.findAll();
           for (int i = 0; i < basketList.size();i++){
               basketList.get(i).getProductInBasketList().removeAll(productInBasketList);
           }
           basketRepository.saveAll(basketList);
         productInBasketRepository.deleteAll(productInBasketList);

        productInBasketRepository.deleteAll();
        productRepository.delete(product);
        return "redirect:/adminallproduct";
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/productupdate/{product}")
    public String updateProduct(@PathVariable Product product,Model model){
       model.addAttribute("productup",product);
        return "/productupdate";
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/updateproduct")
    public String proUpdate(@RequestParam(name = "id")String id,@RequestParam(name = "name")String name,@RequestParam(name = "price")String price,
                            @RequestParam(name = "description")String description,@RequestParam(name = "productmanufactory")String productmanufactory,Model model){
        Product product= productRepository.findById(Long.parseLong(id)).get();
        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setDescription(description);
        ProductManufactory productManufactory11 = productManufactoryRepository.findProductManufactoryByName(productmanufactory);
        product.setProductManufactory(productManufactory11);
        productRepository.save(product);
        model.addAttribute("updateproduct",product);
        return "redirect:/adminallproduct";

    }
}
