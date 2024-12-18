package com.pluralsight.easyshop.data.mysql;

import com.pluralsight.easyshop.data.OrderDao;
import com.pluralsight.easyshop.models.Order;
import com.pluralsight.easyshop.models.OrderLineItem;
import com.pluralsight.easyshop.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlOrderDao extends MySqlProductDao implements OrderDao {

    @Autowired
    MySqlProductDao productDaoImpl;
    MySqlShoppingCartDao shoppingCartDaoImpl;

    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Order create(int userId) {
        /* Requirements:
        *  1) Retrieve the current user's shopping cart
        *  2) Create a new Order
        *  3) Create a new OrderLineItem for each shopping cart item
        *  4) Add OrderLineItems to the db
        *
        * //What's being returned in an order
        *   - OrderLineItems
        *   - User background info (address, city, state, zip) --> can be gotten from user_id joining on users table
        * */

        //Getting the current user's shopping cart
        ShoppingCart userCart = shoppingCartDaoImpl.getByUserId(userId);
        

        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO orders (user_id, date, address, city, state, zip, shipping_amount)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """);
            statement.setInt(1, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
