package com.binh.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface Service_UserDetails {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
