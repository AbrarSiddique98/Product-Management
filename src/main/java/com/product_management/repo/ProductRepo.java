package com.product_management.repo;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product_management.entity.Product;


@Repository

public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query(value = "DELETE FROM category_product WHERE product_product_id=:productId", nativeQuery = true)
	@Modifying
	public void deleteProductIdFromProductCategoryTable(@Param("productId") int productId);

}
