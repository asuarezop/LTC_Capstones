package com.pluralsight.easyshop.data;

import com.pluralsight.easyshop.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color);
    List<Product> listByCategoryId(int categoryId);
    Product getById(int productId);
    Product create(Product product);
    void update(int productId, Product product);
    void delete(int productId);
}
