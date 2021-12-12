package com.binh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.entity.Category;
import com.binh.repository.Repository_Category;
import com.binh.service.Service_Category;

@Service
public class ServiceImpl_Category implements Service_Category{
	@Autowired private Repository_Category repoCate;
	
	@Override
	public List<Category> findAll() {
		return repoCate.findAll();
	}

	@Override
	public Category findById(String id) {
		return repoCate.findById(id).get();
	}
	
}
