package com.employeemanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Team {
	@Id
	@GeneratedValue 
	private Long teamId;
	
	private String teamName;
	@OneToMany(mappedBy ="team")
	@JsonIgnoreProperties("team")
	private List<Employee> employees;

	public Team(String name) {
		super();
		this.teamName = name;
	}

	public Team() {
		super();
	}
	
}
