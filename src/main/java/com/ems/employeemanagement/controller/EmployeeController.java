package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.security.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);}
	@PostMapping("/register")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	@PutMapping("/{id}")
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
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("")
	public List<Employee> getEmployees(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String designation,
			@RequestParam(required = false) Integer minAge,
			@RequestParam(required = false) Integer maxAge,
			@RequestParam(required = false) String vehicleType) {

		if (name != null) {
			// Call the method to find employees by name
			return employeeRepository.findByName(name);
		} else if (designation != null) {
			// Call the method to find employees by designation
			return employeeRepository.findByDesignation(designation);
		} else if (minAge != null ) {
			// Call the method to find employees by age range
			return employeeRepository.findEmployeeByAgeGreaterThan(minAge);
		} else if (maxAge != null ) {
			// Call the method to find employees by age range
			return employeeRepository.findEmployeeByAgeLessThan(maxAge);
		} else if (vehicleType != null) {
			// Call the method to find employees by vehicle type
			return employeeService.findEmployeesByVehicleType(vehicleType);
		} else {
			// Handle the case where none of the parameters are provided
			return employeeRepository.findAll(); // or return all employees if needed
		}
	}
}