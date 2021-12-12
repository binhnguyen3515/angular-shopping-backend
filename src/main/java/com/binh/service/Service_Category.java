package com.binh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.binh.entity.Category;

@Service
public interface Service_Category {

	List<Category> findAll();

	Category findById(String id);

}
