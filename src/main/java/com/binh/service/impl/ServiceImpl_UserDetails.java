package com.binh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.binh.entity.Account;
import com.binh.repository.Repository_Account;
import com.binh.security.service.UserDetailsImpl;

@Service
public class ServiceImpl_UserDetails implements UserDetailsService{

	@Autowired private Repository_Account repoAcc;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = repoAcc.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}

}
