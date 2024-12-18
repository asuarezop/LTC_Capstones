package com.pluralsight.easyshop.controllers;

import com.pluralsight.easyshop.data.ProductDao;
import com.pluralsight.easyshop.data.ShoppingCartDao;
import com.pluralsight.easyshop.data.UserDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/orders")
public class OrdersController {

    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    public OrdersController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

//    @PostMapping
     //public Order create(

}
