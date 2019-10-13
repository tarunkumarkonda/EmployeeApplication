package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Team;

@RepositoryRestResource	
public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Iterable<Team> findAll();
	
	Team findByTeamId(@Param("q") long teamId);
	
	Team findByTeamNameIgnoreCase(@Param("q") String teamName); // fetching team details based on team name
	
	@Query("select e.team.employees from Employee e where e.name = ?1") //getting fellow team mates using emp name
	Iterable<Employee> findFellowEmployeesInTeamByEmpName(@Param("q") String teamName);
	
	@Query("select e from Employee e where e.team.teamName = ?1") //fetching employees using team name
	Iterable<Employee> findTeamEmployeesByTeamName(@Param("q") String name);
	
}
