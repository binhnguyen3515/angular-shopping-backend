package com.binh.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.service.Service_Account;
import com.binh.service.Service_Authority;
import com.binh.service.Service_Order;
import com.binh.service.Service_OrderDetail;
import com.binh.service.Service_Product;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/summary")
public class RestController_Summary {
	@Autowired private Service_Product productService;
	@Autowired private Service_Order orderService;
	@Autowired private Service_OrderDetail orderDetailService;
	@Autowired private Service_Account accountService;
	@Autowired private Service_Authority authService;
	
	@GetMapping("firstRow")
	public Map<String,Object> showSummary(){
		System.out.println("run1");
		Map<String,Object> map = new HashMap<>();
		map.put("todayOrder", orderService.getToDayOrder());
		map.put("totalOrder", orderService.totalOrder());
		map.put("available", productService.getAvailable());
		map.put("totalProduct", productService.getTotalProduct());
		map.put("todayIncome", orderDetailService.getTodayIncome());
		map.put("totalIncome", orderDetailService.getTotalIncome());
		map.put("totalCustomer", authService.getTotalCustomer());
		map.put("totalAccount", accountService.getTotalAccount());
		return map;
	}
	
	@GetMapping("secondRow")
	public Map<String,List<Object[]>> showSecond(){
		Map<String,List<Object[]>> map = new HashMap<>();
		map.put("revenueLast7Days", orderService.getRevenueLast7Days());
		map.put("productSoldByCate", productService.numberOfProductSoldByType());
		return map;
	}
	
	@GetMapping("thirdRow")
	public Map<String,List<Object[]>> showThird(){
		Map<String,List<Object[]>> map = new HashMap<>();
		map.put("percentSoldByCate", productService.getPercentByCate());
		map.put("productAvailableRate", productService.availableRate());
		return map;
	}
	
	@GetMapping("fourthRow")
	public Map<String,List<Object[]>> fourthRow(){
		Map<String,List<Object[]>> map = new HashMap<>();
		map.put("top10SoldProduct", productService.top10Product());
		return map;
	}
	
	@GetMapping("fifthRow")
	public Map<String,List<Object[]>> fifthRow(){
		Map<String,List<Object[]>> map = new HashMap<>();
		map.put("topCustomer", accountService.top10Customer());
		return map;
	}
}
