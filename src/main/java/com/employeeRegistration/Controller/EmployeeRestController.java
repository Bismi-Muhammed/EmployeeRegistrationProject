package com.employeeRegistration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeRegistration.Entity.Employee;
import com.employeeRegistration.Service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	
	@Autowired
	EmployeeService employeeService;
	
	//Method to  save employee registration details
	
	@PostMapping("/saveEmployees")
	public ResponseEntity<String> saveEmployees( Employee employee) {
		System.out.println("entered");
		employeeService.saveEmployee(employee);
		return  ResponseEntity.ok("Employee Saved Successfully");
	}
	
	//method to fetch all employee details
	
	@GetMapping("/admin/fetchAllEmployees")
	public ResponseEntity<List<Employee>> fetchAllEmployees(){
		List<Employee> employeeList=employeeService.fetchAllEmployees();
		return new ResponseEntity<>(employeeList,HttpStatus.OK) ;
	}
	
	
	//method to update employee details
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployees(@PathVariable Integer id,
			 Employee employee){
		Employee currentEmployee = employeeService.fetchEmployeeById(id);
		if (currentEmployee == null) {
			return ResponseEntity.notFound().build();
		}
		currentEmployee.setEmployeeNo(employee.getEmployeeNo());
		currentEmployee.setEmployeeName(employee.getEmployeeName());
		currentEmployee.setJoiningDate(employee.getJoiningDate());
		currentEmployee.setDepartment(employee.getDepartment());
		currentEmployee.setSalary(employee.getSalary());
		Employee updatedEmployee=employeeService.updateEmployee(currentEmployee);
		return  new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
	}
	
	//method to fetch employee details by id
	
	@GetMapping("/fetchEmployeeById/{id}")
	public ResponseEntity<Employee> fetchEmployeeById(@PathVariable  Integer id ){
		Employee employee =employeeService.fetchEmployeeById(id);
		if(employee != null) {
		return new ResponseEntity<>(employee,HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//method to delete delete employee details by id
	
	@DeleteMapping("/deleteEmployeeById/{employeeId}")
	public ResponseEntity<String > deleteEmployeeById(@PathVariable Integer employeeId){
		employeeService.deleteEmployeeById(employeeId);
		return  ResponseEntity.ok("Employee details  Successfully Deleted");
	}
	
	// rest api to search employees based on their employee number

	@GetMapping("searchEmployeeByEmployeeNumber/{empNo}")
	public ResponseEntity<Employee> getEmployeeByNumber(@PathVariable String empNo) {
		Employee fetchedEmp = employeeService.searchEmpByNumber(empNo);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}

	// rest api to search employee based on name

	@GetMapping("searchEmployeeByEmployeeName/{empName}")
	public ResponseEntity<Employee> getEmployeeByName(@PathVariable String empName) {
		Employee fetchedEmp = employeeService.searchEmpByName(empName);
		return new ResponseEntity<>(fetchedEmp, HttpStatus.OK);
	}
	// This method retrieves list of employees under a department

	@GetMapping("/searchEmployeeByDepartment/{departmentId}")
	public ResponseEntity<?> employeesByDepartment(@PathVariable Integer departmentId) {
		List<Employee> employeeList = employeeService.getEmployeesByDepartment(departmentId);
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	

}
