package com.binh.dto;

import java.util.List;

import com.binh.entity.Authority;
import lombok.Data;

@Data
public class DTO_Role {
	private String id;
	private String name;
	List<Authority> authorities;
}
