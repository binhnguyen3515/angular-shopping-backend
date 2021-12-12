package com.binh.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("v1/api/test")
public class RestController_Test {
	@GetMapping("all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("Customers")
	@PreAuthorize("hasAnyAuthority('Customers','Directors','Staffs')")
	public String userAccess() {
		System.out.println("run");
		return "User Content.";
	}

	@GetMapping("Staffs")
	@PreAuthorize("hasAuthority('Staffs')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("Directors")
	@PreAuthorize("hasAuthority('Directors')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
