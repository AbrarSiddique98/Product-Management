package com.product_management.service;

import java.util.List;

import com.product_management.dto.CategoryDto;
import com.product_management.entity.Category; 



public interface CategoryService {

	Category addCategory(Category category);

	String deleteCategoryById(int id) throws Exception;

	List<Category> getAllCategory() throws Exception;

	Category getCategoryById(int id) throws Exception;

	Category updateCategoryById(CategoryDto categoryDto) throws Exception;

}
