package com.binh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.binh.entity.Product;

@Service
public interface Service_Product {

	List<Product> findAll();

	List<Product> findByCategoryId(String cid);

	Page<Product> findAll(Pageable pageable);

	long count();

	Page<Product> findByCategoryId(String cid, Pageable pageable);

	long countByCategoryId(String cid);

	Product findById(Integer productID);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
	//Summary
	Long getAvailable();

	Long getTotalProduct();

	List<Object[]> numberOfProductSoldByType();

	List<Object[]> getPercentByCate();

	List<Object[]> availableRate();

	List<Object[]> top10Product();

}
