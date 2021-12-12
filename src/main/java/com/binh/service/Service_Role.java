package com.binh.service;

import org.springframework.stereotype.Service;

import com.binh.entity.Role;

@Service
public interface Service_Role {

	Role findByName(String string) throws RuntimeException;

}
