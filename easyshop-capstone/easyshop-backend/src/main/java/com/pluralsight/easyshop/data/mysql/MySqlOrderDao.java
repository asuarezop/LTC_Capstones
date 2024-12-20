package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import com.pluralsight.easyshop.data.OrderDao;
import com.pluralsight.easyshop.models.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao {

    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Order createOrder(int userId, ShoppingCart cart, Profile userProfile) {
        LocalDateTime orderDateTime = LocalDateTime.now();
        DateTimeFormatter traditionalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        Order fullOrder;

        //Part 1 - Get User Profile and create a new Order to be saved in the database
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO orders (user_id, date, address, city, state, zip) VALUES
                    (?, ?, ?, ?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setTimestamp(2, Timestamp.valueOf(orderDateTime.format(traditionalDateTime)));
            statement.setString(3, userProfile.getAddress());
            statement.setString(4, userProfile.getCity());
            statement.setString(5, userProfile.getState());
            statement.setString(6, userProfile.getZip());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            ResultSet genKeys = statement.getGeneratedKeys();

            if (genKeys.next()) {
                int orderId = genKeys.getInt(1);

                //Create the order object associated to a user
                fullOrder = new Order(orderId, userId);

                //Part 2 - Adding products to the order
                List<OrderLineItem> lineItems = convertCartToLineItems(fullOrder, cart);

                //Adding additional user background info
                String userAddress = userProfile.getAddress();
                String userCity = userProfile.getCity();
                String userState = userProfile.getState();
                String userZip = userProfile.getZip();
                BigDecimal shippingAmt = BigDecimal.ZERO;
                BigDecimal orderTotal = fullOrder.getOrderTotal();

                return new Order(orderId, userId, orderDateTime, userAddress, userCity, userState, userZip, shippingAmt, lineItems, orderTotal);
            }

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Order was created!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Order addItemsToOrder(int userId, OrderLineItem lineItem) {
        return null;
    }

    private List<OrderLineItem> convertCartToLineItems(Order order, ShoppingCart cart) {
        List<OrderLineItem> lineItems = order.getLineItems();

        // Part 3 - Saving and creating an order line item for each shopping cart item
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO order_line_items (order_id, product_id, sales_price, quantity, discount) VALUES
                    (?, ?, ?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);

            for (ShoppingCartItem item : cart.getItems().values()) {
                //Inserting new order line items into database
                statement.setInt(1, order.getOrderId());
                statement.setInt(2, item.getProductId());
                statement.setBigDecimal(3, item.getProduct().getPrice());
                statement.setInt(4, item.getQuantity());
                statement.setBigDecimal(5, item.getDiscountPercent());

                int rows = statement.executeUpdate();
                System.out.printf("Rows updated: %d\n", rows);

                OrderLineItem lineItem = getOrderLineItem(order.getOrderId(), item, statement);

                //Adding line items to list
                lineItems.add(lineItem);
            }

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Order line item was created!" + ColorCodes.RESET);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lineItems;
    }

    private OrderLineItem getOrderLineItem(int orderId, ShoppingCartItem item, PreparedStatement statement) throws SQLException {
        //Getting primary key for order line item
        ResultSet genKeys = statement.getGeneratedKeys();
        genKeys.next();
        int orderLineItemId = genKeys.getInt(1);

        //Creating order line item representation to be included in HTTP response
        int productId = item.getProductId();
        String productName = item.getProduct().getName();
        BigDecimal productPrice = item.getProduct().getPrice();
        int quantity = item.getQuantity();
        BigDecimal discount = item.getDiscountPercent();
        BigDecimal orderLineTotal = item.getLineTotal();

        return new OrderLineItem(orderLineItemId, orderId, productId, productName, productPrice, quantity, discount, orderLineTotal);
    }
}
