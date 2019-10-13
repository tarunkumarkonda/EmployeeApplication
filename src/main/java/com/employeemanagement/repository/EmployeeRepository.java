package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employeemanagement.entity.Employee;

@RepositoryRestResource	
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	@Query("select e from Employee e where e.employeeId = ?1") // Query used to eliminate Optional type
	Employee findByEmployeeId(@Param("q") Long employeeId);
	
	Employee findByNameIgnoreCase(@Param("q") String name);
	
	Iterable<Employee> findBySalaryGreaterThanEqual(@Param("q") double salary);
	
	Iterable<Employee> findBySalaryLessThanEqual(@Param("q") double salary);
	
	Iterable<Employee> findByTeamTeamNameIgnoreCase(@Param("q") String teamName); //working
	
	Iterable<Employee> findByManagerManagerNameIgnoreCase(@Param("q") String manager); //working
	
	Iterable<Employee> findByActiveTrue();
	
	Iterable<Employee> findByActiveFalse();
	
	Iterable<Employee> findByYearsOfServiceGreaterThanEqual(@Param("q") Integer years); // issue
	
	Iterable<Employee> findByYearsOfServiceLessThanEqual(@Param("q") Integer years); //issue
	
	
}
