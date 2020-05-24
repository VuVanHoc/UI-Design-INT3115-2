package com.uet.int3315.nhom8.license_test_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mentor")
public class MentorController {
	
	@GetMapping(value = "/dashboard")
	public String showDashboard(){
		System.out.println("MENTOR DASHBOARD");
		return "mentorDashboard";
	}
}
