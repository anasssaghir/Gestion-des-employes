package org.sid.service;

import java.util.List; 
import java.util.Optional;

import org.sid.model.Employee;
import org.sid.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.PageRequest; 
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.Sort; 
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam; 

@Service 
public class EmployeeServiceImpl implements EmployeeService { 
	@Autowired 
	private EmployeeRepository employeeRepository; 
	@Override 
	public List < Employee > getAllEmployees() { 
		return employeeRepository.findAll(); 
	} 
	@Override 
	public void saveEmployee(Employee employee) { 
		this.employeeRepository.save(employee); 
	} 
	@Override 
	public Employee getEmployeeById(long id) { 
		Optional < Employee > optional = employeeRepository.findById(id); 
		Employee employee = null; 
		if (optional.isPresent()) { 
			employee = optional.get(); 
		} else { 
			throw new RuntimeException(" Employee not found for id :: " + id); 
		} 
		return employee; 
	} 
	@Override 
	public void deleteEmployeeById(long id) { 
		this.employeeRepository.deleteById(id); 
	} 
	@Override 
	public Page < Employee > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) { 
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending(); 
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); 
		return this.employeeRepository.findAll(pageable); 
	} 
	@GetMapping(path="/index")
	   public String test(Model model,
			   @RequestParam(name="page",defaultValue="0") int page, 
			   @RequestParam(name="size",defaultValue="3") int size,
			   @RequestParam(name="keyword",defaultValue="") String mc) {
		   Page<Employee> pagepatients=employeeRepository.findByFirstNameContains(mc,PageRequest.of(page, size));
		   model.addAttribute("listePatients",pagepatients.getContent());
		   model.addAttribute("page", new int [pagepatients.getTotalPages()]);
		   model.addAttribute("currentPage", page);
		   model.addAttribute("keyword", mc);
	         return "index";
	   }
	@Override
	public Page<Employee> findByFirstNameContains(String mc, Pageable pageRequest) {
		return employeeRepository.findByFirstNameContains(mc,pageRequest);
	}
}
