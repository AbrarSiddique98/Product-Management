package com.product_management.controller;

import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product_management.entity.Product;
import com.product_management.service.ProductService;



@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws Exception {
        logger.info("Request received to fetch all products");
        List<Product> products = productService.getAllProduct();
        logger.info("Successfully fetched {} products", products.size());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info("Request received to add a new product: {}", product);
        Product savedProduct = productService.addProduct(product);
        logger.info("Product added successfully with ID: {}", savedProduct.getProductId());
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) throws Exception {
        logger.info("Request received to delete product with ID: {}", id);
        String result = productService.deleteProductById(id);
        logger.info("Product with ID {} deleted successfully", id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws Exception {
        logger.info("Request received to fetch product with ID: {}", id);
        Product product = productService.getProductById(id);
        logger.info("Product fetched successfully: {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product product) throws Exception {
        logger.info("Request received to update product with ID: {}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        logger.info("Product with ID {} updated successfully: {}", id, updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
