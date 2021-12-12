package com.binh.controller.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binh.dto.DTO_Product;
import com.binh.entity.Category;
import com.binh.entity.Product;
import com.binh.service.Service_Category;
import com.binh.service.Service_Product;
import com.binh.service.mapper.Mapper_Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/products")
public class RestController_Product {
	@Autowired private Service_Product productService;
	@Autowired private Service_Category cateService;
	@Autowired private Mapper_Product productMapper;
	
//	@GetMapping
//	public List<DTO_Product> getAll(){
//		List<Product> list = productService.findAll();
//		return list.stream().map(e->productMapper.convertEntityToDto(e)).collect(Collectors.toList());
//	}
//	
//	@GetMapping("{cid}")
//	public List<DTO_Product> getByCateId(@PathVariable("cid")String cid){
//		List<Product> list = productService.findByCategoryId(cid);
//		return list.stream().map(e->productMapper.convertEntityToDto(e)).collect(Collectors.toList());
//	}
//	@GetMapping("{cid}")
//	public Page<DTO_Product> getByCateId(@PathVariable("cid")String cid){
//		Pageable pageable = PageRequest.of(0, 6);
//		Page<Product> list = productService.findByCategoryId(cid,pageable);
//		long totalItems = productService.countByCategoryId(cid);
//		return new PageImpl<>(list.stream().map(e->productMapper.convertEntityToDto(e)).collect(Collectors.toList()),pageable,totalItems);
//	}
	@GetMapping()
	public Page<DTO_Product> getAll(
			@RequestParam(value="page",defaultValue = "0")Optional<Integer>p,
			@RequestParam(value="cid",defaultValue = "null")Optional<String>cid,
			@RequestParam(value="sortType",defaultValue = "default message")Optional<String>sort){
		
		Pageable pageable = PageRequest.of(p.orElse(0), 6);
		Page<Product> list = null;
		Sort sortOption = null;
		long totalItems = 0;
		//sort by category, no other sorts
		if(!cid.get().equals("null") && sort.get().equals("default message")) {
			list = productService.findByCategoryId(cid.get(),pageable);
			totalItems = productService.countByCategoryId(cid.get());
		}
		//no sorts
		if(cid.get().equals("null") && sort.get().equals("default message")) {
			list = productService.findAll(pageable);
			totalItems = productService.count();
		}
		//not sort by category, sort by price + date
		if(cid.get().equals("null") && !sort.get().equals("default message")) {
			if(sort.get().equals("priceDown")) {
				sortOption = Sort.by(Direction.DESC,"price");
			}
			if(sort.get().equals("priceUp")) {
				sortOption = Sort.by(Direction.ASC,"price");
			}
			if(sort.get().equals("newDate")) {
				sortOption = Sort.by(Direction.DESC,"createDate");
			}
			if(sort.get().equals("oldDate")) {
				sortOption = Sort.by(Direction.ASC,"createDate");
			}
			pageable = PageRequest.of(p.orElse(0), 6,sortOption);
			list = productService.findAll(pageable);
			totalItems = productService.count();
		}
		//sort by category, sort by price + date
		if(!cid.get().equals("null") && !sort.get().equals("default message")) {
			if(sort.get().equals("priceDown")) {
				sortOption = Sort.by(Direction.DESC,"price");
			}
			if(sort.get().equals("priceUp")) {
				sortOption = Sort.by(Direction.ASC,"price");
			}
			if(sort.get().equals("newDate")) {
				sortOption = Sort.by(Direction.DESC,"createDate");
			}
			if(sort.get().equals("oldDate")) {
				sortOption = Sort.by(Direction.ASC,"createDate");
			}
			pageable = PageRequest.of(p.orElse(0), 6,sortOption);
			list = productService.findByCategoryId(cid.get(),pageable);
			totalItems = productService.countByCategoryId(cid.get());
		}
		if(cid.get().equals("all")) {
			list = productService.findAll(pageable);
			totalItems = productService.count();
		}
		return new PageImpl<>(list.stream().map(e->productMapper.convertEntityToDto(e)).collect(Collectors.toList()),pageable,totalItems);
	}
	
	@GetMapping("detail/{id}")
	public DTO_Product getDetailedProduct(@PathVariable("id")Integer productID) {
		Product productInfo = productService.findById(productID);
		return productMapper.convertEntityToDto(productInfo);
	}
	
	@GetMapping("cateID/{cid}")
	public List<DTO_Product>getProductByCateID(@PathVariable("cid")String cid){
		List<Product> list = productService.findByCategoryId(cid);
		return list.stream().map(e->productMapper.convertEntityToDto(e)).collect(Collectors.toList());
	}
	
	//Admin section
	@GetMapping("list")
	public List<Product> getAll(){
		return productService.findAll();
	}
	
	@PostMapping
	public Product create(@RequestBody DTO_Product productDTO) {
		Product product = productMapper.convertToEntity(productDTO);
		Category cate = cateService.findById(productDTO.getCategory().getId());
		product = productService.create(product);
		product.setCategory(cate);
		return product;
	}
	
	@PutMapping("{id}")
	public Product update(@RequestBody DTO_Product productDTO,@PathVariable("id")Integer id) {
		Product product = productMapper.convertToEntity(productDTO);
		Category cate = cateService.findById(productDTO.getCategory().getId());
		product = productService.update(product);
		product.setCategory(cate);
		return product;
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		productService.delete(id);
	}
}
