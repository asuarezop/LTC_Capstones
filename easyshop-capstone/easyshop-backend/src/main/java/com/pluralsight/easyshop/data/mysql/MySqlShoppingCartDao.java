package com.pluralsight.easyshop.data.mysql;

import com.pluralsight.easyshop.data.ShoppingCartDao;
import com.pluralsight.easyshop.models.Product;
import com.pluralsight.easyshop.models.ShoppingCart;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM shopping_cart
                    JOIN products ON products.product_id = shopping_cart.product_id
                    WHERE user_id = ?
                    """);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
//                ShoppingCart userCart = mapRow(rs);
//                return userCart;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ShoppingCart addProductToCart(int userId, Product Product) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
//                    INSERT INTO shopping_cart(
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ShoppingCart();
    }

//    private ShoppingCart mapRow(ResultSet row) throws SQLException {
//        int userId = row.getInt("user_id");
//        int productId = row.getInt("product_id");
//        int quantity = row.getInt("quantity");
//
//        return new ShoppingCart(userId, productId, quantity);
//    }
}
