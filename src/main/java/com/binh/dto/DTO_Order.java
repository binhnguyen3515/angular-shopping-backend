package com.binh.dto;

import java.util.Date;
import java.util.List;

import com.binh.entity.Account;
import com.binh.entity.OrderDetail;
import lombok.Data;

@Data
public class DTO_Order {
	Long id;
	String address;
	Date createDate = new Date();
	Account account;
	List<OrderDetail> orderDetails;
}
