package com.binh.dto;

import com.binh.entity.Account;
import com.binh.entity.Role;

import lombok.Data;

@Data
public class DTO_Authority {
	private Integer id;
	private Account account;
	private Role role;
}
