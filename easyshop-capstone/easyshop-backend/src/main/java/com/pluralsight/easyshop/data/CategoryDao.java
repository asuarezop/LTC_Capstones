package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();
    Category getById(int categoryId);
    Category create(Category category);
    void update(int categoryId, Category category);
    void delete(int categoryId);
}
