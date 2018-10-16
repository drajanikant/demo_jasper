package com.example.jasper_demo.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer emp_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name= "salary")
	private Double salary;

	public Employee() {
		super();
		this.emp_id = 0;
		this.name = "";
		this.designation = "";
		this.salary = 0.0;
	} 
	
	public Employee(Integer emp_id, String name, String designation, Double salary) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", name=" + name + ", designation=" + designation + ", salary=" + salary
				+ "]";
	} 
	
	
}
