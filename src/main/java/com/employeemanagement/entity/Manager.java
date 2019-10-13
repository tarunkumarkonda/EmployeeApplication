package com.employeemanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Manager {
	@Id
	@GeneratedValue 
	
	private long managerId;
	
	@NotNull
	private String managerName;
	
	@OneToMany(mappedBy = "manager")
	@JsonIgnoreProperties("manager")
	private List<Employee> employees;

	public Manager(String name) {
		super();
		this.managerName = name;
	}
	
	public Manager() {
		super();
	}

	/*
	@OneToMany(mappedBy = "manager" )
	private List<Team> teams;*/
	
}
