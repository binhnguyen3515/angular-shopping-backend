package com.binh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.entity.Authority;
import com.binh.repository.Repository_Authority;
import com.binh.service.Service_Authority;

@Service
public class ServiceImpl_Authority implements Service_Authority{
	
	@Autowired private Repository_Authority repoAuth;
	
	@Override
	public void saveAll(List<Authority> roles) {
		repoAuth.saveAll(roles);
	}

	@Override
	public void removeRoleByUsername(String username) {
		repoAuth.removeByUsername(username);
		
	}

	@Override
	public List<Authority> findByUsername(String username) {
		return repoAuth.findByUsername(username);
	}

	/*Summary*/
	@Override
	public Long getTotalCustomer() {
		return repoAuth.getTotalCustomer();
	}
	
	
}
