package com.employeemanagement.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Manager;
import com.employeemanagement.entity.Team;
import com.employeemanagement.exception.NoDataFoundException;
import com.employeemanagement.services.EmployeeService;
import com.google.common.collect.Lists;

@RestController
public class EmployeeController {
	
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/AllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		List<Employee> employees = Lists.newArrayList(service.findAllEmployees());
		
		if(!employees.isEmpty()) {
			logger.info("Employees are not null");
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
			
		}else {
			logger.info("No Employees");
			throw new NoDataFoundException("No Data found for requested criteria"); 
		}
	}
	@GetMapping("/EmployeeById/{employeeId}")
	//changed query to custom to remove optional RT
	public ResponseEntity<Employee> findEmployeebyId(@PathVariable long employeeId){
		
		Employee employee = service.findByEmployeeId(employeeId);
		
		if(!(employee == null)) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}else {
			throw new NoDataFoundException("No Data found for requested criteria");
		}	
	}
	
	@GetMapping("/EmployeeByName/{name}")
	public ResponseEntity<Employee> findEmployeeByName(@PathVariable String name){
		
		Employee employee = service.findByName(name);
		
		if(!(employee == null)) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}else {
			throw new NoDataFoundException("No Data found for requested criteria");
		}	
	}
	// Flag = greater than or Equal
	@GetMapping("/EmployeeBySalary/{salary}/{flag}")
	public ResponseEntity<List<Employee>> findEmployeeBySalary(@PathVariable double salary,@PathVariable boolean flag ){
		
			if(flag) {
				List<Employee> emp1 =  Lists.newArrayList(service.findBySalaryGreaterThan(salary));
				if(!(emp1.isEmpty())) {
					return new ResponseEntity<List<Employee>>(emp1, HttpStatus.OK);
				}else {
					throw new NoDataFoundException("No Data found for requested criteria");
				}
			}
			else {
				List<Employee> emp2 =  Lists.newArrayList(service.findBySalaryLessThan(salary));
				if(!(emp2.isEmpty())) {
					return new ResponseEntity<List<Employee>>(emp2, HttpStatus.OK);
				}else {
					throw new NoDataFoundException("No Data found for requested criteria");
				}
			}
	}
	
	@GetMapping("/ActiveInActiveEmployees/{flag}")
	public  ResponseEntity<List<Employee>> findActiveInActiceEmployees(@PathVariable boolean flag){
		// scope for improving the validation for data receiving
		List<Employee> employee = Lists.newArrayList(service.findActiveOrInActiveEmployees(flag));
		if(!(employee.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		}else {
			throw new NoDataFoundException("No Data found for requested criteria");
		}
		
	}
	
	@GetMapping("/EmployeesByYearsOfService/{years}/{flag}") 
	public ResponseEntity<List<Employee>> findEmployeesByService(@PathVariable int years,@PathVariable boolean flag){
		// Issue 
		if(flag) { 
			List<Employee> employees = Lists.newArrayList(service.findEmployeesByServiceGreaterOrEqual(years));
			
			if(!(employees.isEmpty())) {
				
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
			}else {
				logger.info("No Employee found");
				throw new NoDataFoundException("No Data found for requested criteria");
			}
		}
		else {
			
			List<Employee> employees = Lists.newArrayList(service.findByEmployeesByServiceLessThanOrEqual(years));
			
			if(!(employees.isEmpty())) {
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
			}else {
				logger.info("No Employee found");
				throw new NoDataFoundException("No Data found for requested criteria");
			}
		}
		
	}
	
	@GetMapping("/findEmployeesByTeamName/{team}")
	public  ResponseEntity<List<Employee>> findEmployeesByTeamName(@PathVariable String team){
		
		List<Employee> employee = Lists.newArrayList(service.findEmployeesByTeam(team));
		
		if(!(employee.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
		
	}
	
	
	// Manager End Points
	
	@GetMapping("/AllManagers")
	public ResponseEntity<List<Manager>> findAllManagers(){
		List<Manager> managers= Lists.newArrayList(service.findAllManagers());
		
		if(!(managers.isEmpty())) {
			return new ResponseEntity<List<Manager>>(managers, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	@GetMapping("/findEmployeesByManagerName/{manager}")
	public  ResponseEntity<List<Employee>> findEmployeesByManagerName(@PathVariable String manager){
		
		List<Employee> employee = Lists.newArrayList(service.findEmployeesByManager(manager));
		
		if(!(employee.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		}else {
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	@GetMapping("/findEmployeesSharingSameManagerByEmpName/{EmpName}")
	public ResponseEntity<List<Employee>> findEmployeesSharingSameManagerByEmpName(@PathVariable String EmpName){
		
		List<Employee> employees = Lists.newArrayList(service.findEmployeesSharingSameManagerByEmpName(EmpName));
		
		if(!(employees.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	@GetMapping("/findEmployeesSharingSameManagerByManName/{ManName}")
	public ResponseEntity<List<Employee>> findEmployeesSharingSameManagerByManName(@PathVariable String ManName){
		
		List<Employee> employees = Lists.newArrayList(service.findEmployeesSharingSameManagerByManName(ManName));
		
		if(!(employees.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	// Team End Points
	@GetMapping("/AllTeams")
	public ResponseEntity<List<Team>> findAllTeams(){
		List<Team> teams = Lists.newArrayList(service.findAllTeams());
		
		if(!(teams.isEmpty())) {
			return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	@GetMapping("/findTeamByTeamName/{teamName}")
	public ResponseEntity<Team> findTeamByTeamname(@PathVariable String teamName){
		
		Team team = service.findTeamByTeamName(teamName);
		
		if(!(team == null)) {
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	@GetMapping("/findFellowEmployeesInTeamByEmpName/{empName}")
	public ResponseEntity<List<Employee>> findFellowEmployeesInTeamByEmpName(@PathVariable String empName){
		
		List<Employee> employees = Lists.newArrayList(service.findFellowEmployeesInTeamByEmpName(empName));
		
		if(!(employees.isEmpty())) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		}else {
			logger.info("No Employee found");
			throw new NoDataFoundException("No Data found for requested criteria");
		}
	}
	
	 @GetMapping("/findTeamEmployeesByTeamName/{teamName}")
	public ResponseEntity<List<Employee>> findTeamEmployeesByTeamName(@PathVariable String teamName){
		
		 List<Employee> employees = Lists.newArrayList(service.findTeamEmployeesByTeamName(teamName));
		 
		 if(!(employees.isEmpty())) {
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
			}else {
				logger.info("No Employee found");
				throw new NoDataFoundException("No Data found for requested criteria");
			}
	}
	 
	 /*Post*/
	 @PostMapping("/AddNewEmployee")
	 public  ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		 		service.saveEmployee(employee);
		 return new ResponseEntity<Employee>(HttpStatus.CREATED);
	 }
	 
	 /*Put for Edit and In activating */
	 @PutMapping("/EditEmployee")
	 public  ResponseEntity<Employee> editEmployee(@RequestBody Employee employee){
		service.editEmployee(employee);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	 }
	 
	 @PutMapping("/InActivateEmployee")
	 public ResponseEntity<Employee> inActivateEmployee(@RequestBody Employee employee){
		 service.deActivateEmployee(employee);
		 return new ResponseEntity<Employee>(HttpStatus.OK);
	 }
	 
}
	
	
