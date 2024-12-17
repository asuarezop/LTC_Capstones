package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import org.springframework.stereotype.Component;
import com.pluralsight.easyshop.models.Product;
import com.pluralsight.easyshop.data.ProductDao;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProductDao extends MySqlDaoBase implements ProductDao {

    public MySqlProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        List<Product> products = new ArrayList<>();

        //Assigning and checking for null values in passed args for use in SQL search query
        categoryId = (categoryId == null) ? -1 : categoryId;
        minPrice = (minPrice == null) ? new BigDecimal("-1") : minPrice;
        maxPrice = (maxPrice == null) ? new BigDecimal("-1") : maxPrice;
        color = (color == null) ? "" : color;

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM products
                    WHERE (category_id = ? OR ? = -1)
                    AND (price >= ? OR ? = -1)
                    AND (price <= ? OR ? = -1)
                    AND (color = ? OR ? = '')
                    """);
            statement.setInt(1, categoryId);
            statement.setInt(2, categoryId);
            statement.setBigDecimal(3, minPrice);
            statement.setBigDecimal(4, minPrice);
            statement.setBigDecimal(5, maxPrice);
            statement.setBigDecimal(6, maxPrice);
            statement.setString(7, color);
            statement.setString(8, color);

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                Product product = mapRow(row);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public List<Product> listByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM products
                    WHERE category_id = ?
                    """);
            statement.setInt(1, categoryId);

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                Product product = mapRow(row);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    @Override
    public Product getById(int productId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM products
                    WHERE product_id = ?
                    """);
            statement.setInt(1, productId);

            ResultSet row = statement.executeQuery();

            if (row.next()) {
                return mapRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO products(name, price, category_id, description, color, image_url, stock, featured) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getColor());
            statement.setString(6, product.getImageUrl());
            statement.setInt(7, product.getStock());
            statement.setBoolean(8, product.isFeatured());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int orderId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    return getById(orderId);
                }
            }

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Product was created!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int productId, Product product) {
//        String sql = "UPDATE products" +
//                " SET name = ? " +
//                "   , price = ? " +
//                "   , category_id = ? " +
//                "   , description = ? " +
//                "   , color = ? " +
//                "   , image_url = ? " +
//                "   , stock = ? " +
//                "   , featured = ? " +
//                " WHERE product_id = ?;";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE products
                    SET name = ?, price = ?, category_id = ?, description = ?, color = ?, image_url = ?, stock = ?, featured = ?
                    WHERE product_id = ?
                    """);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getColor());
            statement.setString(6, product.getImageUrl());
            statement.setInt(7, product.getStock());
            statement.setBoolean(8, product.isFeatured());
            statement.setInt(9, productId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Product was updated." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int productId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM products
                    WHERE product_id = ?
                    """);
            statement.setInt(1, productId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Product removed from database." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Product mapRow(ResultSet row) throws SQLException {
        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        return new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
    }
}
