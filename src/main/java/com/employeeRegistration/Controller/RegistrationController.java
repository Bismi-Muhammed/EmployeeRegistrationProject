package com.employeeRegistration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.employeeRegistration.Entity.Department;
import com.employeeRegistration.Entity.Employee;
import com.employeeRegistration.Service.EmployeeService;

import org.springframework.ui.Model;

@Controller
public class RegistrationController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/employee_register")
	public String employeeRegitster(Model model) {
		List<Department> departments = employeeService.findAllDepartment();
		model.addAttribute("departments", departments);
		return "employeeRegister";
	}

	@GetMapping("/employee_list")
	public String employeeList(Model model) {
		List<Employee> employeeList = employeeService.fetchAllEmployees();
		model.addAttribute("employees", employeeList);
		return "employeeList";
	}

}
