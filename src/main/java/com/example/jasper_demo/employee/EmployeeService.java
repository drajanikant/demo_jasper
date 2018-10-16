package com.example.jasper_demo.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employee_repository;
	
	public void setEmployee_repository(EmployeeRepository employee_repository) {
		this.employee_repository = employee_repository;
	}
	
	@Override
	public List<Map<String, Object>> getEmployeeDetails() {
		List<Map<String, Object>> employees_details = new ArrayList<>();
		employee_repository.findAll().forEach(employee -> {
			Map<String, Object> emp = new HashMap<>();
			emp.put("emp_id", employee.getEmp_id());
			emp.put("name", employee.getName());
			emp.put("designation", employee.getDesignation());
			emp.put("salary", employee.getSalary());
			
			employees_details.add(emp);
		});
		
		return employees_details;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<>();
		employee_repository.findAll().forEach(employee -> list.add(employee));
		
		return list;
	}

}
