package com.product_management.service.Impl;

import java.util.List; 

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_management.entity.Category;
import com.product_management.entity.Product;
import com.product_management.repo.CategoryRepo;
import com.product_management.repo.ProductRepo;
import com.product_management.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {

	private static  Logger LOGGER=LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Product addProduct(Product product) {
		LOGGER.info("addProduct Execution Started ");
		int categoryId = product.getCategory().getCategoryId();

		Category category = categoryRepo.findById(categoryId).get();
		List<Product> prodList = category.getProduct();
		prodList.add(product);
		categoryRepo.save(category);
		return product;
	}

	@Override
	public Product updateProduct(int id, Product product) throws Exception {
		LOGGER.info("updateProduct Execution Started ");
		Product existingProduct = productRepo.findById(product.getProductId()).get();
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductPrice(product.getProductPrice());
		return productRepo.save(product);
	}

	@Override
	@Transactional
	public String deleteProductById(int id) throws Exception {
		LOGGER.info("deleteProductById Execution Started ");
		Product product2 = productRepo.findById(id)
		.orElseThrow(() -> new Exception("Product Doesn't Exists with id : " + id));
		productRepo.deleteProductIdFromProductCategoryTable(id);
		productRepo.deleteById(product2.getProductId());
		return "Product Deleted With ID: "+ id;

	}

	@Override
	public List<Product> getAllProduct() throws Exception {
		LOGGER.info("getAllProduct Execution Started ");
		List<Product> products = productRepo.findAll();

		if (products == null) {
			throw new Exception("Products are Not exists in Data");
		}
		return products;
	}

	@Override
	public Product getProductById(int id) throws Exception {
		LOGGER.info("getProductById Execution Started ");
		return productRepo.findById(id).orElseThrow(() -> new Exception("Product Doesn't Exists with id : " + id));
	}

}
