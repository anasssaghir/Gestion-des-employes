package org.sid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

import org.sid.model.Employee;
import org.sid.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
@SpringBootApplication
public class GestionEmployeeApplication implements  CommandLineRunner {
    
	@Autowired
	private EmployeeRepository employeeRepository ;
	public static void main(String[] args) {
		SpringApplication.run(GestionEmployeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
