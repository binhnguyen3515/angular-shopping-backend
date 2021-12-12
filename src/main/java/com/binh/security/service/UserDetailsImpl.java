package com.binh.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.binh.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;

	private String username;
	@JsonIgnore
	private String password;
	private String fullname;
	private String email;
	private String photo;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, String fullname, String email, String photo,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.photo = photo;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Account user) {
		List<GrantedAuthority> authorities = user.getAuthorities().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getUsername(), 
				user.getPassword(), 
				user.getFullname(), 
				user.getEmail(),
				user.getPhoto(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}
}
