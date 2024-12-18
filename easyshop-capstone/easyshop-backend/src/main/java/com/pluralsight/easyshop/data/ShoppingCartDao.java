package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Product;
import com.pluralsight.easyshop.models.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCart addProductToCart(int userId, Product product);
    void clearCart(int userId);
}
