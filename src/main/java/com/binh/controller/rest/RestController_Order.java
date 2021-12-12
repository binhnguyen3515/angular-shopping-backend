package com.binh.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.dto.DTO_Order;
import com.binh.entity.Order;
import com.binh.service.Service_Order;
import com.binh.service.Service_OrderDetail;
import com.binh.service.mapper.Mapper_Order;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/orders")
public class RestController_Order {
	@Autowired private Service_Order orderService;
	@Autowired private Service_OrderDetail orderDetail;
	@Autowired private Mapper_Order orderMapper;
	
	@PostMapping
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@GetMapping("list/{username}")
	public List<DTO_Order> getOrderByUsername(@PathVariable("username")String username){
		List<Order> list = orderService.findByUsername(username);
		return list.stream().map(e->orderMapper.convertEntityToDto(e)).collect(Collectors.toList());
	}
	
	@GetMapping("detail/{id}")
	public DTO_Order findById(@PathVariable("id")Long id) {
		System.out.println("íd: "+id);
		Order item = orderService.findById(id);
		return orderMapper.convertEntityToDto(item);
	}
	
}
