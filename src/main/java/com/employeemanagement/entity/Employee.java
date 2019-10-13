package com.employeemanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id @GeneratedValue long employeeId;
	
	 String name;
	 int yearsOfService;
	 double salary;
	
	 boolean active;
	
	@ManyToOne
	@JsonIgnoreProperties("employees")
	 Manager manager;
	
	@ManyToOne
	@JsonIgnoreProperties("employees")
	 Team team;
	
	public Employee(String employeeName,boolean active, int years, double salary, Manager manager, Team team) {
		super();
		this.active = active;
		this.name = employeeName;
		this.yearsOfService = years;
		this.salary = salary;
		this.manager = manager;
		this.team = team;
	}
	public Employee() {
		
	}
	
	
}
