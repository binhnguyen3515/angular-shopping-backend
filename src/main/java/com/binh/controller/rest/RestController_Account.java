package com.binh.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.dto.DTO_Account;
import com.binh.entity.Account;
import com.binh.service.Service_Account;
import com.binh.service.mapper.Mapper_Account;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/accounts")
public class RestController_Account {
	@Autowired private Service_Account accService;
	@Autowired private Mapper_Account accMapper;
	
	@GetMapping("list")
	public List<DTO_Account> getAll() {
		List<Account> list = accService.findAll();
		return list.stream().map(e->accMapper.convertEntityToDto(e)).collect(Collectors.toList());
	}
}
