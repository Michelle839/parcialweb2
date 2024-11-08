package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Employee;
import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> list() {

		return employeeService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Integer id) {
		Optional<Employee> Employee = employeeService.getById(id);
		return Employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/salary")
	public ResponseEntity<Employee> getEmployeeWithSalary(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.getEmployeeWithSalaryById(id);
		return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{employeeId}/departments/{departmentId}")
	public ResponseEntity<Employee> addEmployeeToDepartment(@PathVariable Integer employeeId,
			@PathVariable Integer departmentId) {
		Optional<Employee> employeeOpt = employeeService.addEmployeeToDepartment(employeeId, departmentId);

		return employeeOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Employee create(@RequestBody Employee Employee) {
		return employeeService.create(Employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee EmployeeDetails) {
		Optional<Employee> updatedEmployee = employeeService.update(id, EmployeeDetails);
		return updatedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{employeeId}/assign-to-project")
    public ResponseEntity<ProjectAssignment> assignToProjectWithRole(
            @PathVariable Integer employeeId,
            @RequestParam Integer projectId,
            @RequestParam Integer roleId) {

        Optional<ProjectAssignment> assignment = employeeService.assignToProjectWithRole(employeeId, projectId, roleId);

        return assignment.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = employeeService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
