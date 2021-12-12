package com.binh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.binh.entity.Account;

@Service
public interface Service_Account {

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	void save(Account user);

	Account findById(String username);

	List<Account> findAll();

	Long getTotalAccount();

	List<Object[]> top10Customer();

}
