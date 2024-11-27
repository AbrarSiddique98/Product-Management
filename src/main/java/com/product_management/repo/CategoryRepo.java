package com.product_management.repo;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.product_management.entity.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
