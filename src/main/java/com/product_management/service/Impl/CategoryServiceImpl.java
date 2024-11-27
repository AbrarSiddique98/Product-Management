package com.product_management.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_management.dto.CategoryDto;
import com.product_management.entity.Category;
import com.product_management.repo.CategoryRepo;
import com.product_management.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {

	private static  Logger LOGGER=LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) {
		LOGGER.info("addCategory Execution Started ");
		return categoryRepo.save(category);
	}

	@Override
	public Category updateCategoryById(CategoryDto categoryDto) throws Exception {
		Category c = categoryRepo.findById(categoryDto.getCategoryId()).orElseThrow(() -> new Exception("Category Not found with Id :" + categoryDto.getCategoryId()));

		//categoryRepo.delete(c);
		c.setCategoryName(categoryDto.getCategoryName());
		return categoryRepo.save(c);
	}

	@Override
	public String deleteCategoryById(int id) throws Exception {
		Category c = categoryRepo.findById(id).orElseThrow(() -> new Exception("Category Not found with Id :" + id));
		categoryRepo.delete(c);
		return "Category Deleted From Database";
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		List<Category> categories = categoryRepo.findAll();
		if (categories.isEmpty()) {
			throw new Exception("There is No Categories are Present in the DataBase ");
		}
		return categories;
	}

	@Override
	public Category getCategoryById(int id) throws Exception {

		return categoryRepo.findById(id).orElseThrow(() -> new Exception("Category Not found with Id :" + id));
	}

}
