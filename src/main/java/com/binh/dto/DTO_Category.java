package com.binh.dto;

import java.util.List;

import com.binh.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DTO_Category {
	private String id;
	private String name;
	@JsonIgnore
	private List<Product> products;
}
