package com.example.jasper_demo.employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
	
	List<Map<String, Object>> getEmployeeDetails();
	
	List<Employee> getEmployees();
}
