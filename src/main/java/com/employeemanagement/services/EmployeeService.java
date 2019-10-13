package com.employeemanagement.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Manager;
import com.employeemanagement.entity.Team;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.repository.ManagerRepository;
import com.employeemanagement.repository.TeamRepository;

@Service
public class EmployeeService {
	
	@Autowired 
	EmployeeRepository employeeRepo;
	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	TeamRepository teamRepo;
	
	public Iterable<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee findByName(String employeeName) {
		return employeeRepo.findByNameIgnoreCase(employeeName);
	}

	public Employee findByEmployeeId(long employeeId) {
		return employeeRepo.findByEmployeeId(employeeId);
	}
	public Iterable<Employee> findBySalaryGreaterThan(double salary){
		return employeeRepo.findBySalaryGreaterThanEqual(salary);
	}
	
	public Iterable<Employee> findBySalaryLessThan(double salary){
		return employeeRepo.findBySalaryLessThanEqual(salary);
	}
	
	public Iterable<Employee> findEmployeesByTeam(String name){
		return employeeRepo.findByTeamTeamNameIgnoreCase(name);
	}
	
	public Iterable<Employee> findEmployeesByManager(String name){
		return employeeRepo.findByManagerManagerNameIgnoreCase(name);
	}
	
	public Iterable<Employee> findActiveOrInActiveEmployees(boolean flag){
		if(flag) {
			return employeeRepo.findByActiveTrue();
		}
		else {
			return employeeRepo.findByActiveFalse();
		}
	}
	
	public Iterable<Employee> findEmployeesByServiceGreaterOrEqual(int years){
		return employeeRepo.findByYearsOfServiceGreaterThanEqual(years);
	}
	
	public Iterable<Employee> findByEmployeesByServiceLessThanOrEqual(int years){
		return employeeRepo.findByYearsOfServiceLessThanEqual(years);
	}
	/* Manager service queries */
	
	public Iterable<Manager> findAllManagers(){
		return managerRepo.findAll();
	}
	public Iterable<Employee> findEmployeesSharingSameManagerByEmpName(String name){
		return managerRepo.findEmployeesSharingSameManagerByEmpName(name);
	}	
	
	public Manager findEmployeesByManagerName(String name) {
		return managerRepo.findByManagerName(name);
	}
	
	public Iterable<Employee> findEmployeesSharingSameManagerByManName(String name){
		return managerRepo.findEmployeesSharingSameManagerByManName(name);
	}
	
	/* Team service queries*/
	
	public Iterable<Team> findAllTeams(){
		return teamRepo.findAll();
	}
	
	public Team findTeamByTeamName(String teamName) { // May be needs to change
		return teamRepo.findByTeamNameIgnoreCase(teamName);
	}
	
	public Iterable<Employee>  findFellowEmployeesInTeamByEmpName(String teamName){
		return teamRepo.findFellowEmployeesInTeamByEmpName(teamName);
	}
	
	public Iterable<Employee> findTeamEmployeesByTeamName(String name){
		return teamRepo.findTeamEmployeesByTeamName(name);
	}
	
	/* Post functions*/
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	/* Put */
	public void editEmployee(Employee employee) {
		Employee existingEmp = findByEmployeeId(employee.getEmployeeId());
		existingEmp.setName(employee.getName());
		existingEmp.setSalary(employee.getSalary());
		existingEmp.setYearsOfService(employee.getYearsOfService());
		existingEmp.setActive(employee.isActive());;
		existingEmp.setManager(employee.getManager());
		existingEmp.setTeam(employee.getTeam());
		employeeRepo.save(existingEmp);
	}
	
	/* InActive an employee  instead of deleting */
	public void deActivateEmployee(Employee employee) {  
		Employee existingEmp = findByEmployeeId(employee.getEmployeeId());
		//existingEmp.setName(employee.getName());
		//existingEmp.setSalary(employee.getSalary());
		//existingEmp.setYearsOfService(employee.getYearsOfService());
		existingEmp.setActive(false);;
		//existingEmp.setManager(employee.getManager());
		//existingEmp.setTeam(employee.getTeam());
		
		employeeRepo.save(existingEmp);
	}
	
}
