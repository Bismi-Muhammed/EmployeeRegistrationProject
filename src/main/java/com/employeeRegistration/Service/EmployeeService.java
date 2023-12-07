package com.employeeRegistration.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeRegistration.Repository.DepartmentRepository;
import com.employeeRegistration.Exception.EmptyInputException;
import com.employeeRegistration.Entity.Department;
import com.employeeRegistration.Entity.Employee;
import com.employeeRegistration.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepo;

	
	public Employee saveEmployee(Employee employee) {
		if (employee.getEmployeeName().isEmpty() || employee.getEmployeeName().length() == 0) {
			throw new EmptyInputException("Input fields are empty");
		}
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;

	}

	public List<Department> findAllDepartment() {
		return departmentRepo.findAll();
	}

	public List<Employee> fetchAllEmployees() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;

	}

	public Employee fetchEmployeeById(Integer employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee.get();
	}

	public void deleteEmployeeById(Integer employeeId) {
		employeeRepository.deleteById(employeeId);

	}

	public Employee updateEmployee(Employee currentEmployee) {
		Employee updatedEmployee = employeeRepository.save(currentEmployee);
		return updatedEmployee;
	}

	public List<Employee> getEmployeesByDepartment(Integer departmentId) {
		List<Employee> employeeList = employeeRepository.findByDepartment(departmentId);
		return employeeList;
	}

	public Employee searchEmpByName(String empName) {
		Employee fetchedEmployee = employeeRepository.findByEmployeeName(empName);
		if (fetchedEmployee == null) {
			throw new NoSuchElementException("Data doesnt existt in DB.It's database layer issue");
		}
		return fetchedEmployee;
	}

	public Employee searchEmpByNumber(String empNo) {
		Employee fetchedEmployee = employeeRepository.findByEmployeeNo(empNo);
		if (fetchedEmployee == null) {
			throw new NoSuchElementException("Data doesnt existt in DB.It's database layer issue");
		}
		return fetchedEmployee;
	}
}
