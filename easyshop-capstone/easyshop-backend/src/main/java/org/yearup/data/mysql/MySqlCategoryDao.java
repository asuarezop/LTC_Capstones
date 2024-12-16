package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    private final DataSource dataSource;
    private List<Category> categories;

    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
        this.categories = new ArrayList<>();
    }

    @Override
    public List<Category> getAllCategories() {
        Category c;
        // get all categories
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM categories;
                    """);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                c = createContractObj(rs);
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
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM categories
                    WHERE category_id = ?
                    """);
            statement.setInt(1, categoryId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                c = createContractObj(rs);
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Category create(Category category) {
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        // update category
    }

    @Override
    public void delete(int categoryId) {
        // delete category
    }

    private Category createContractObj(ResultSet row) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

//        Category category = new Category() {{
//            setCategoryId(categoryId);
//            setName(name);
//            setDescription(description);
//        }};

        return new Category(categoryId, name, description);
    }

}
