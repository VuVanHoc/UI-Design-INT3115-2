package com.uet.int3315.nhom8.license_test_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "Signin";
	}
	
	@GetMapping(value = "/student/dashboard")
	public String showDashboard(){
		System.out.println("STUDENT DASHBOARD");
		
		return "exam";
	}
}
