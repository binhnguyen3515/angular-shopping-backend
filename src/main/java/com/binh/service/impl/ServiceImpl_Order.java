package com.binh.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.entity.Order;
import com.binh.entity.OrderDetail;
import com.binh.repository.Repository_Order;
import com.binh.repository.Repository_OrderDetail;
import com.binh.service.Service_Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class ServiceImpl_Order implements Service_Order{

	@Autowired private Repository_Order repoOrder;
	@Autowired private Repository_OrderDetail repoOrderDetail;
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		
		repoOrder.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(o->o.setOrder(order)).collect(Collectors.toList());
		repoOrderDetail.saveAll(details);
		return order;
	}

	@Override
	public List<Order> findByUsername(String username) {
		return repoOrder.findByUsername(username);
	}

	@Override
	public Order findById(Long id) {
		return repoOrder.findById(id).get();
	}

	@Override
	public Long getToDayOrder() {
		return repoOrder.getToDayOrder();
	}

	@Override
	public Long totalOrder() {
		return repoOrder.count();
	}

	@Override
	public List<Object[]> getRevenueLast7Days() {
		return repoOrder.getRevenueLast7Days();
	}

}
