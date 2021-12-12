package com.binh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.entity.Role;

public interface Repository_Role extends JpaRepository<Role, String>{

	Role findByName(String string) throws RuntimeException;

}
