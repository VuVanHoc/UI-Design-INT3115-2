package com.uet.int3315.nhom8.license_test_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@GetMapping(value = "/dashboard")
	public String showDashboard() {
		System.out.println("ADMIN DASHBOARD");
		return "Layout";
	}
	
	@GetMapping(value = "/courses")
	public String showCourses() {
		return "Courses";
	}
	
	@GetMapping(value = "/history")
	public String showHistory() {
		return "history";
	}
	
	@GetMapping(value = "/manage-user")
	public String manageUser() {
		return "UsersData";
	}
	
	
}
