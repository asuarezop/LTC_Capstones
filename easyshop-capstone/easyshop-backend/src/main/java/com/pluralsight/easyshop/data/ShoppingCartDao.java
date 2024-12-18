package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Product;
import com.pluralsight.easyshop.models.ShoppingCart;
import com.pluralsight.easyshop.models.ShoppingCartItem;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    ShoppingCart addProductToCart(int userId, Product product);
    void updateProductInCart(int userId, ShoppingCartItem item, int productId);
    void clearCart(int userId);
}
