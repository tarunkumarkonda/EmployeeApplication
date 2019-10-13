package com.employeemanagement;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Manager;
import com.employeemanagement.entity.Team;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.repository.ManagerRepository;
import com.employeemanagement.repository.TeamRepository;

@SpringBootApplication
public class EmployeeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeProjectApplication.class, args);}
	
	
	@Bean
	CommandLineRunner initData(EmployeeRepository employeeRepo, ManagerRepository managerRepo, TeamRepository teamRepo) {
		return args-> {
			
			Team t1 = teamRepo.save( new Team("Development"));
			Team t2 = teamRepo.save( new Team("Database"));
			Team t3 = teamRepo.save( new Team("Infrastructure"));
			Team t4 = teamRepo.save( new Team("Testing"));
			Team t5 = teamRepo.save( new Team("Users"));
			Team t6 = teamRepo.save( new Team("Management"));
			
			Manager m1 = managerRepo.save(new Manager("Development-Manager"));
			Manager m2 = managerRepo.save(new Manager("Database-Manager"));
			Manager m3 = managerRepo.save(new Manager("Infrastructure-Manager"));
			Manager m4 = managerRepo.save(new Manager("Testing-Manager"));
			Manager m5 = managerRepo.save(new Manager("Users-Manager"));
			Manager m6 = managerRepo.save(new Manager("Management-Manager"));
			
			employeeRepo.save(new Employee("Emp1",true, 5,25000, m1, t1  ));
			employeeRepo.save(new Employee("Emp2", true,2 ,25000, m1, t1  ));
			
			employeeRepo.save(new Employee("Emp3", true,3,25000, m2, t2  ));
			employeeRepo.save(new Employee("Emp4",true, 4,25000, m2, t2  ));
			
			employeeRepo.save(new Employee("Emp5",true, 6,25000, m3, t3  ));
			employeeRepo.save(new Employee("Emp6",true, 7,25000, m3, t3  ));
			
			employeeRepo.save(new Employee("Emp7",true, 8,35000, m4, t4  ));
			employeeRepo.save(new Employee("Emp8",true, 8,35000, m4, t4  ));
			
			employeeRepo.save(new Employee("Emp9",false, 4,45000, m5, t5  ));
			employeeRepo.save(new Employee("Emp10",false, 4,45000, m5, t5  ));
			
			employeeRepo.save(new Employee("Emp11",false, 7,55000, m6, t6  ));
			employeeRepo.save(new Employee("Emp12",false, 7,55000, m6, t6  ));	
			
		};
}
}
	
	


