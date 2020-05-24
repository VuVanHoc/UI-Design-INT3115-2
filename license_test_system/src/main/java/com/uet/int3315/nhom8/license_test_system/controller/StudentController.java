package com.uet.int3315.nhom8.license_test_system.controller;

import com.uet.int3315.nhom8.license_test_system.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
	@Autowired
	ExamResultService examResultService;
	
	@GetMapping(value = {"/", "/login", "/logout"})
	public String index() {
		return "Signin";
	}
	
	@GetMapping(value = "/student/dashboard")
	public String showDashboard(){
		return "student/dashboard";
	}

	@GetMapping(value = "/student/courses")
	public String showCourses(){
		return "student/courses";
	}

	@GetMapping(value = "/student/courses/{courseId}/examDetail")
	public String showExamDetail(Model model, @PathVariable Integer courseId){
		model.addAttribute("courseId", courseId);
		return "student/examDetail";
	}

	@GetMapping(value = "/student/courses/{courseId}/exam")
	public String showExam(Model model, @PathVariable Integer courseId){
		model.addAttribute("courseId", courseId);
		return "student/exam";
	}

	@GetMapping(value = "/student/history")
	public String showHistory(){
		return "student/history";
	}

//	@GetMapping(value = "/authenticate")
//	public String authenticate(){
//		return "authenticate";
//	}
}
