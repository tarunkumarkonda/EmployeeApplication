package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Manager;

@RepositoryRestResource
public interface ManagerRepository extends CrudRepository<Manager, Long> {
	
		
		
		@Query("select m from Manager m where m.managerId =?1")
		Manager findByManagerId(@Param("q") long managerId);
		@Query("select m from Manager m where m.managerName =?1")
		Manager findByManagerName(@Param("q") String managerName);
		
		@Query("select e.manager.employees from Employee e where e.name = ?1") // employees sharing same manager
		Iterable<Employee> findEmployeesSharingSameManagerByEmpName(@Param("q") String name);
		
		@Query("select e from Employee e where e.manager.managerName = ?1 ") //employees working by by Manager name
		Iterable<Employee> findEmployeesSharingSameManagerByManName(@Param("q") String name);

}
