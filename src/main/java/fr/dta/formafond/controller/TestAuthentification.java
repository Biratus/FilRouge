package fr.dta.formafond.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestAuthentification {
	
	public TestAuthentification() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/public",method=RequestMethod.GET)
	public String publicAccess() {
		return "public completed";
	}
	
	@RequestMapping(value="/private",method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('User')")
	public String privateAccess() {
		return "private Access";
	}
	
	@RequestMapping(value="/private/admin",method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('Admin')")
	public String privateAdminAccess() {
		return "private Admin Access";
	}
	

}
