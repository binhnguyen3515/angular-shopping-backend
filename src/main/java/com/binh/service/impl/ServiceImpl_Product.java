package com.binh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.binh.entity.Product;
import com.binh.repository.Repository_Product;
import com.binh.service.Service_Product;

@Service
public class ServiceImpl_Product implements Service_Product{
	@Autowired private Repository_Product repoProduct;
	
	@Override
	public List<Product> findAll() {
		return repoProduct.findAll();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return repoProduct.findByCategoryId(cid);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return repoProduct.findAll(pageable);
	}

	@Override
	public long count() {
		return repoProduct.count();
	}

	@Override
	public Page<Product> findByCategoryId(String cid, Pageable pageable) {
		return repoProduct.findByCategoryId(cid,pageable);
	}

	@Override
	public long countByCategoryId(String cid) {
		// TODO Auto-generated method stub
		return repoProduct.countByCategoryId(cid);
	}

	@Override
	public Product findById(Integer productID) {
		return repoProduct.findById(productID).get();
	}

	@Override
	public Product create(Product product) {
		return repoProduct.save(product);
	}

	@Override
	public Product update(Product product) {
		return repoProduct.save(product);
	}

	@Override
	public void delete(Integer id) {
		repoProduct.deleteById(id);
	}

	@Override
	public Long getAvailable() {
		return repoProduct.getAvailable();
	}

	@Override
	public Long getTotalProduct() {
		return repoProduct.count();
	}

	@Override
	public List<Object[]> numberOfProductSoldByType() {
		return repoProduct.numberOfProductSoldByType();
	}

	@Override
	public List<Object[]> getPercentByCate() {
		return repoProduct.getPercentByCate();
	}

	@Override
	public List<Object[]> availableRate() {
		return repoProduct.availableRate();
	}

	@Override
	public List<Object[]> top10Product() {
		return repoProduct.top10Product();
	}
	
}
