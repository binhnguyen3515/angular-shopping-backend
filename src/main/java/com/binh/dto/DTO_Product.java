package com.binh.dto;

import java.util.Date;
import java.util.List;

import com.binh.entity.Category;
import com.binh.entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DTO_Product {
	private Integer id;
	private String name;
	private String image;
	private Double price;

	private Date createDate = new Date();
	private Boolean available;

	private Category category;
	@JsonIgnore
	private List<OrderDetail> orderDetails;	
}
