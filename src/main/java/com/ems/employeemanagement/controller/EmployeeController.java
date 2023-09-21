package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String welcome(){
		return "Welcome to webApplication";
	}

	@GetMapping("employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
//
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
												   @RequestBody Map<String, Object> updates) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if (!optionalEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Employee employee = optionalEmployee.get();
		if (updates.containsKey("name")) { employee.setName((String) updates.get("name")); }
		if (updates.containsKey("age")) { employee.setAge((Integer) updates.get("age")); }
		if (updates.containsKey("designation")) { employee.setDesignation((String) updates.get("designation")); }
		if (updates.containsKey("salary")) { employee.setSalary((int) updates.get("salary")); }
		if (updates.containsKey("work_experience")) {employee.setWork_experience((int) updates.get("work_experience"));
		}
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	//Hello world
	//new line added from testing

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	//employee/findbyDesignation?designation=manager
	@GetMapping("employees/findbyDesignation")
	public List<Employee> getEmployeesByDesignation(@RequestParam String designation) {
		return employeeService.getEmployeesByDesignation(designation);
	}
	@GetMapping("/employees/findbyName")
	//employee/findbyName?name=manager
	public List<Employee> getEmployeeByName(@RequestParam String name){
		return employeeService.getEmployeesByname(name);
	}
	//employees/byAgeRange?minAge=x&maxAge=y
	@GetMapping("employees/byAgeRange")
	public List<Employee> getEmployeesByAgeRange(
			@RequestParam int minAge,
			@RequestParam int maxAge) {
		return employeeService.getEmployeesByAgeRange(minAge, maxAge);
	}

	//employees/ByVehicleType?vehicleType=YourVehicleType
	@GetMapping("employees/ByVehicleType")
	public List<Employee> getEmployeesByVehicleType(@RequestParam String vehicleType) {
		return employeeService.findEmployeesByVehicleType(vehicleType);
	}
}

