package com.pluralsight.easyshop.controllers;

import com.pluralsight.easyshop.data.*;
import com.pluralsight.easyshop.models.Order;
import com.pluralsight.easyshop.models.Profile;
import com.pluralsight.easyshop.models.ShoppingCart;
import com.pluralsight.easyshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping(path = "/orders")
@PreAuthorize("hasRole('ROLE_USER')")
public class OrderController {

    private OrderDao orderDao;
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public OrderController(OrderDao orderDao, ShoppingCartDao shoppingCartDao, UserDao userDao, ProfileDao profileDao) {
        this.orderDao = orderDao;
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @PostMapping
    public Order checkout(Principal principal) {
        try {
            // get the currently logged in username
            String userName = principal.getName();
            // find database user by userId
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            //Get the user's shopping cart
            ShoppingCart userCart = shoppingCartDao.getByUserId(userId);

            //Get the user's profile
            Profile userProfile = profileDao.getByUserId(userId);

            // use orderDao to create a new order
            return orderDao.createOrder(userId, userCart, userProfile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

}
