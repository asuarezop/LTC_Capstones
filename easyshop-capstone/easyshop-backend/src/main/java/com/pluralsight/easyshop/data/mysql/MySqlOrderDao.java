package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import com.pluralsight.easyshop.data.OrderDao;
import com.pluralsight.easyshop.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao {

    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }

//    @Override
//    public Order getOrderById(int userId) {
//        String queryOne = """
//                SELECT * FROM orders
//                JOIN order_line_items ON orders.order_id = order_line_items.order_id
//                WHERE user_id = ?
//                """;
//
//        try (Connection conn = getConnection()) {
//            PreparedStatement statement = conn.prepareStatement("""
//                    SELECT orders.order_id, user_id, date, address, city, state, zip, shipping_amount, order_line_items.product_id, products.name, order_line_items.sales_price, order_line_items.quantity, order_line_items.discount
//                    FROM orders
//                    JOIN order_line_items ON orders.order_id = order_line_items.order_id
//                    JOIN products ON products.product_id = order_line_items.product_id
//                    WHERE user_id = ?;
//                    """);
//            statement.setInt(1, userId);
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                //Create an Order object
//                String address = rs.getString("address");
//                String city = rs.getString("city");
//                String state = rs.getString("state");
//                String zip = rs.getString("zip");
//                BigDecimal shippingAmt = rs.getBigDecimal("shipping_amount");
////                Order userOrder = new Order(userId, LocalDateTime.now(), address, city, state, zip, shippingAmt);
//
//
//
//
//                for (ShoppingCartItem item: userCart.getItems().values()) {
//                    //Create order line items
//                    int productId = rs.getInt("order_line_items.product_id");
//                    String name = rs.getString("products.name");
//                    double salesPrice = rs.getDouble("order_line_items.sales_price");
//                    int quantity = rs.getInt("order_line_items.quantity");
//                    BigDecimal discount = rs.getBigDecimal("order_line_items.discount");
//
////                    OrderLineItem lineItem = new OrderLineItem(productId, name, salesPrice, quantity, discount);
//
//
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return new Order();
//    }

    @Override
    public Order createOrder(int userId, ShoppingCart cart, Profile userProfile) {
        String orderDateTime = getOrderDateTime(LocalDateTime.now());

        //Part 1 - Get User Profile and create a new Order to be saved in the database
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO orders (user_id, date, address, city, state, zip) VALUES
                    (?, ?, ?, ?, ?, ?)
                    """);
            statement.setInt(1, userId);
            statement.setString(2, orderDateTime);
            statement.setString(3, userProfile.getAddress());
            statement.setString(4, userProfile.getCity());
            statement.setString(5, userProfile.getState());
            statement.setString(6, userProfile.getZip());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Order was created!" + ColorCodes.RESET);

            //Adding products to the order (2nd step)
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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




        //Last step
        return null;
    }

    @Override
    public Order addItemsToOrder(int userId, OrderLineItem lineItem) {
        return null;
    }

    private String getOrderDateTime(LocalDateTime dateTime) {
        DateTimeFormatter traditionalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return dateTime.format(traditionalDateTime);
    }
}
