package com.binh.dto;

import java.util.List;

import com.binh.entity.Authority;
import com.binh.entity.Order;
import lombok.Data;

@Data
public class DTO_Account {
	String username;
	String password;
	String fullname;
	String email;
	String photo;
	List<Order> orders;
	List<Authority> authorities;
}
