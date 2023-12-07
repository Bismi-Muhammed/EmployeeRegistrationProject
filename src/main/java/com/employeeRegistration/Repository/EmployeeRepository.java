package com.employeeRegistration.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeRegistration.Entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Employee findByEmployeeNo(String empNo);

	Employee findByEmployeeName(String empName);

	List<Employee> findByDepartment(Integer departmentId);

}
