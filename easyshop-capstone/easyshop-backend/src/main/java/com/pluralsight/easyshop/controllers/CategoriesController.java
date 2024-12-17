package com.pluralsight.easyshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.pluralsight.easyshop.data.CategoryDao;
import com.pluralsight.easyshop.data.ProductDao;
import com.pluralsight.easyshop.models.Category;
import com.pluralsight.easyshop.models.Product;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
// http://localhost:8080/categories
// add annotation to allow cross site origin requests
@RestController
@CrossOrigin
@RequestMapping(path = "/categories")
public class CategoriesController {
    // create an Autowired controller to inject the categoryDao and ProductDao
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    // add the appropriate annotation for a get action
    @GetMapping
    public List<Category> getAll() {
        // find and return all categories
        return categoryDao.getAllCategories();
    }

    // add the appropriate annotation for a get action
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Category getById(@PathVariable Integer id) {
        // get the category by id
        Category foundCategory = categoryDao.getById(id);

        if (foundCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return foundCategory;
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable Integer categoryId) {
        // get a list of product by categoryId
        return productDao.listByCategoryId(categoryId);
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        // insert the category
        return categoryDao.create(category);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        // update the category by id
        categoryDao.update(id, category);
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @RequestMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        // delete the category by id
        categoryDao.delete(id);
    }
}
