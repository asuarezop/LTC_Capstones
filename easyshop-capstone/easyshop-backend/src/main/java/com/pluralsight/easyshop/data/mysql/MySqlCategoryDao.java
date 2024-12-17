package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import org.springframework.stereotype.Component;
import com.pluralsight.easyshop.data.CategoryDao;
import com.pluralsight.easyshop.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {

    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Category c;
        // get all categories
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM categories;
                    """);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                c = createCategoryObj(rs);
                categories.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId) {
        Category c;
        // get category by id
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM categories
                    WHERE category_id = ?
                    """);
            statement.setInt(1, categoryId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                c = createCategoryObj(rs);
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Category create(Category category) {
        Category c;
        // create a new category
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    INSERT INTO categories(name, description) VALUES
                    (?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            ResultSet genKey = statement.getGeneratedKeys();

            if (genKey.next()) {
                int categoryId = genKey.getInt(1);
                c = new Category(categoryId, category.getName(), category.getDescription());
                return c;
            }

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Category was created!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        // update category
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    UPDATE categories
                    SET name = ?, description = ?
                    WHERE category_id = ?
                    """);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Category was updated." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int categoryId) {
        // delete category
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    DELETE FROM categories
                    WHERE category_id = ?
                    """);
            statement.setInt(1, categoryId);

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Category removed from database." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category createCategoryObj(ResultSet row) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        return new Category(categoryId, name, description);
    }
}
