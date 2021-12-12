package com.binh.controller.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.entity.Role;
import com.binh.entity.Account;
import com.binh.entity.Authority;
import com.binh.security.payload.JwtResponse;
import com.binh.security.payload.LoginRequest;
import com.binh.security.payload.MessageResponse;
import com.binh.security.payload.SignupRequest;
import com.binh.security.service.UserDetailsImpl;
import com.binh.service.Service_Account;
import com.binh.service.Service_Authority;
import com.binh.service.Service_Role;
import com.binh.utils.JwtUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/api/rest/auth")
public class RestController_Auth {
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private Service_Authority authService;
	@Autowired private Service_Account accService;
	@Autowired private Service_Role roleService;
	@Autowired private PasswordEncoder encoder;
	@Autowired private JwtUtils jwtUtils;
	
	@PostMapping("signin")
	public ResponseEntity<?>authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Wrong account information!"));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
//		System.out.println("detail:"+userDetails.getUsername()+"--"+userDetails.getPassword());
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping("signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (accService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (accService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Account user = new Account(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFullname(),
							 signUpRequest.getPhoto());

		Set<String> strRoles = signUpRequest.getRole();
		List<Authority> roles = new ArrayList<>();

		if (strRoles == null) {
			Role userRole = roleService.findByName("Customers");
			roles.add(new Authority(user, userRole));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "Directors":
					Role adminRole = roleService.findByName("Directors");
					roles.add(new Authority(user, adminRole));
					break;
				case "Staffs":
					Role modRole = roleService.findByName("Staffs");
					roles.add(new Authority(user, modRole));

					break;
				default:
					Role userRole = roleService.findByName("Customers");
					roles.add(new Authority(user, userRole));
				}
			});
		}

		accService.save(user);
		authService.saveAll(roles);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest,
			@PathVariable("id")String username) {
		// Update user's account
		Account user = new Account(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFullname(),
							 signUpRequest.getPhoto());

		Set<String> strRoles = signUpRequest.getRole();
		List<Authority> roles = new ArrayList<>();

		if (strRoles == null) {
			Role userRole = roleService.findByName("Customers");
			roles.add(new Authority(user, userRole));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "Directors":
					Role adminRole = roleService.findByName("Directors");
					roles.add(new Authority(user, adminRole));
					break;
				case "Staffs":
					Role modRole = roleService.findByName("Staffs");
					roles.add(new Authority(user, modRole));
					break;
				default:
					Role userRole = roleService.findByName("Customers");
					roles.add(new Authority(user, userRole));
				}
			});
		}
		authService.removeRoleByUsername(username);
		accService.save(user);
		authService.saveAll(roles);
		return ResponseEntity.ok(new MessageResponse("Updated account successfully!"));
	}
	
	@GetMapping("getOne/{id}")
	public ResponseEntity<?>getOne(@PathVariable("id")String username){
		Account acc = accService.findById(username);
		List<Authority> authority = authService.findByUsername(username);
		Set<String> strRoles = authority.stream().map(e->e.getRole().getName()).collect(Collectors.toSet());

		return ResponseEntity.ok(new SignupRequest(acc.getUsername(),acc.getFullname(),acc.getEmail(),acc.getPhoto(),
				strRoles,acc.getPassword()));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?>getAll(){
		List<Account> acc = accService.findAll();
		List<SignupRequest> list = acc.stream().map(e-> 
		new SignupRequest(e.getUsername(), e.getFullname(), e.getEmail(), e.getPhoto(),null, e.getPassword())).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
}
