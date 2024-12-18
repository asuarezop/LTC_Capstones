package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import com.pluralsight.easyshop.data.ProductDao;
import com.pluralsight.easyshop.data.ShoppingCartDao;
import com.pluralsight.easyshop.models.Product;

import com.pluralsight.easyshop.models.ShoppingCart;
import com.pluralsight.easyshop.models.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    @Autowired
    MySqlProductDao productDaoImpl;

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart userCart = new ShoppingCart();
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM shopping_cart
                    JOIN products ON products.product_id = shopping_cart.product_id
                    WHERE user_id = ?
                    """);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                //the only thing that can be added into shopping cart are shopping cart items
                ShoppingCartItem item = new ShoppingCartItem();

                //Mapping products associated with a user
                Product p = productDaoImpl.mapRow(rs);

                item.setProduct(p);
                item.setQuantity(rs.getInt("quantity"));

                //Adding items to shopping cart
                userCart.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userCart;
    }

    @Override
    public ShoppingCart addProductToCart(int userId, Product product) {
        //Getting user's shopping cart to add items into
        ShoppingCart userCart = getByUserId(userId);

        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO shopping_cart (user_id, product_id) VALUES
                    (?, ?)
                    """);
            statement.setInt(1, userId);
            statement.setInt(2, product.getProductId());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Shopping cart was updated." + ColorCodes.RESET);

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);

            //Adding new items to user's shopping cart
            userCart.add(item);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userCart;
    }

    @Override
    public void updateProductInCart(int userId, ShoppingCartItem item, int productId) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    UPDATE shopping_cart
                    SET quantity = ?
                    WHERE user_id = ? AND product_id = ?
                    """);
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, userId);
            statement.setInt(3, productId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Product in cart was updated." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(int userId) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM shopping_cart
                    WHERE user_id = ?
                    """);
            statement.setInt(1, userId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Shopping cart has been cleared." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
