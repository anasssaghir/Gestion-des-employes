package org.sid.repository;

import org.sid.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

	
	@Repository 
	public interface EmployeeRepository extends JpaRepository<Employee, Long>{ 
		public Page<Employee> findByFirstNameContains(String mc, Pageable pageRequest);

	}