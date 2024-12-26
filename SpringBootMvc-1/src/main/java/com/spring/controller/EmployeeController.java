package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.empModel.EmployeeModel;
@Controller
public class EmployeeController {
	@GetMapping("/employee")
	public String home()
	{
		return "employee";
	}
	@PostMapping("/submit")
	public String saveDetails(EmployeeModel employeeModel) {
		return "success";
	}
	
}
