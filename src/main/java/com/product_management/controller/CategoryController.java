package com.product_management.controller;

import java.util.ArrayList; 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_management.dto.CategoryDto;
import com.product_management.entity.Category;
import com.product_management.service.CategoryService;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/categories/all")
    public ResponseEntity<List<Category>> getAllCategories() throws Exception {
        logger.info("Request received to fetch all categories");
        List<Category> categories = categoryService.getAllCategory();
        logger.info("Successfully fetched {} categories", categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> addCategories(@RequestBody CategoryDto categoryDto) throws Exception {
        logger.info("Request received to add a new category: {}", categoryDto);
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setProduct(new ArrayList<>());
        Category savedCategory = categoryService.addCategory(category);
        logger.info("Category added successfully with ID: {}", savedCategory.getCategoryId());
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoriesById(@PathVariable int id) throws Exception {
        logger.info("Request received to fetch category by ID: {}", id);
        Category category = categoryService.getCategoryById(id);
        logger.info("Category fetched successfully: {}", category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/categories")
    public ResponseEntity<Category> updateCategories(@RequestBody CategoryDto categoryDto) throws Exception {
        logger.info("Request received to update category: {}", categoryDto);
        Category updatedCategory = categoryService.updateCategoryById(categoryDto);
        logger.info("Category updated successfully: {}", updatedCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable int id) throws Exception {
        logger.info("Request received to delete category with ID: {}", id);
        String result = categoryService.deleteCategoryById(id);
        logger.info("Category deleted successfully: {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
