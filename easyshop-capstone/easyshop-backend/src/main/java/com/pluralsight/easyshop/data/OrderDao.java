package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Order;
import com.pluralsight.easyshop.models.OrderLineItem;
import com.pluralsight.easyshop.models.Profile;
import com.pluralsight.easyshop.models.ShoppingCart;

public interface OrderDao {
//    Order getOrderById(int userId);
    Order createOrder(int userId, ShoppingCart cart, Profile userProfile);
    Order addItemsToOrder(int userId, OrderLineItem lineItem);
}
