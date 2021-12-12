package com.binh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.entity.Role;
import com.binh.repository.Repository_Role;
import com.binh.service.Service_Role;

@Service
public class ServiceImpl_Role implements Service_Role{

	@Autowired private Repository_Role repoRole;
	
	@Override
	public Role findByName(String string) {
		return repoRole.findByName(string);
	}

}
