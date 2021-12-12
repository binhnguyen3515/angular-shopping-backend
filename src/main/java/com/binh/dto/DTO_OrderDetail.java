package com.binh.dto;

import com.binh.entity.Order;
import com.binh.entity.Product;

import lombok.Data;

@Data
public class DTO_OrderDetail {
	private Long id;
	private Double price;
	private Integer quantity;
	private Product product;
	private Order order;
}
