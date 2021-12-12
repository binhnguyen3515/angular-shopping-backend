package com.binh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.binh.entity.Account;
import com.binh.repository.Repository_Account;
import com.binh.service.Service_Account;

@Service
public class ServiceImpl_Account implements Service_Account{

	@Autowired private Repository_Account repoAcc;

	@Override
	public Boolean existsByUsername(String username) {
		return repoAcc.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return repoAcc.existsByEmail(email);
	}

	@Override
	public void save(Account user) {
		repoAcc.save(user);
		
	}

	@Override
	public Account findById(String username) {
		return repoAcc.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return repoAcc.findAll();
	}

	@Override
	public Long getTotalAccount() {
		return repoAcc.getTotalAccount();
	}

	@Override
	public List<Object[]> top10Customer() {
		return repoAcc.top10Customer();
	}

//	@Override
//	public Account loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@Override
//	@Transactional
//	public ServiceImpl_Account loadUserByUsername(String username) throws UsernameNotFoundException {
//		Account account = repoAcc.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
//		return ServiceImpl_Account.build(account);
//	}
//
//	private Collection<? extends GrantedAuthority> authorities;
//	private String username;
//	private String password;
//	private String fullname;
//	private String email;
//	private String photo;
//	
//	public ServiceImpl_Account( String username, String password,
//			String fullname, String email, String photo,Collection<? extends GrantedAuthority> authorities) {
//		
//		this.username = username;
//		this.password = password;
//		this.fullname = fullname;
//		this.email = email;
//		this.photo = photo;
//		this.authorities = authorities;
//	}
//
//	public static ServiceImpl_Account build(Account account) {
//		List<GrantedAuthority> authorities = account.getAuthorities().stream()
//				.map(role->new SimpleGrantedAuthority(role.getRole().getName()))
//				.collect(Collectors.toList());
//		return new ServiceImpl_Account(
//				account.getUsername(),
//				account.getPassword(),
//				account.getFullname(),
//				account.getEmail(),
//				account.getPhoto(),
//				authorities
//				);
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getFullname() {
//		return fullname;
//	}
//
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(String photo) {
//		this.photo = photo;
//	}
//	
//	
}
