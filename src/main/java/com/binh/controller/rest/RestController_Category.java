package com.binh.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.dto.DTO_Category;
import com.binh.dto.DTO_Product;
import com.binh.entity.Category;
import com.binh.entity.Product;
import com.binh.service.Service_Category;
import com.binh.service.Service_Product;
import com.binh.service.mapper.Mapper_Category;
import com.binh.service.mapper.Mapper_Product;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/categories")
public class RestController_Category {
	@Autowired private Service_Category cateService;
//	@Autowired private Mapper_Category cateMapper;
	
	@GetMapping
	public List<Category> getAll(){
		return cateService.findAll();
	}
}
