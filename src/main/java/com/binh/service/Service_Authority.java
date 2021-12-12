package com.binh.service;

import java.util.List;

import com.binh.entity.Authority;

public interface Service_Authority {

	void saveAll(List<Authority> roles);

	void removeRoleByUsername(String username);

	List<Authority> findByUsername(String username);

	Long getTotalCustomer();
	
}
