package com.samco.springbootH2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samco.springbootH2.EmployeeRepository.EmployeeRepository;
import com.samco.springbootH2.ManagerRepository.ManagerRepository;
import com.samco.springbootH2.primary.Employee;
import com.samco.springbootH2.secondary.Manager;

@SpringBootApplication
@RestController
public class SpringBootconnectTwoDatabaseApplication {
	@Autowired
	EmployeeRepository employeeRepository; 
	@Autowired
	 ManagerRepository  managerRepository;
	
	
	@PostMapping
	public void savedata() {
		employeeRepository.saveAll(Stream.of(new Employee(2, "kalaiselvan","10000")).collect(Collectors.toList()));
		managerRepository.saveAll(Stream.of(new Manager(3, "dileep","10000")).collect(Collectors.toList()));
	}
	

	@GetMapping("/emp")
	public List<Employee> getPrimaryDatabaseData() {
	
		return employeeRepository.findAll();
	}
	
	@GetMapping("/manager")
	public List<Manager> getSecondaryDatabaseData() {
		
		return managerRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootconnectTwoDatabaseApplication.class, args);
	}

}
