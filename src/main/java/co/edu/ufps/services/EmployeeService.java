package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Department;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.entities.Project;
import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.entities.Role;
import co.edu.ufps.repositories.DepartmentRepository;
import co.edu.ufps.repositories.EmployeeRepository;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
import co.edu.ufps.repositories.ProjectRepository;
import co.edu.ufps.repositories.RoleRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private ProjectAssignmentRepository projectAssignmentRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	

	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> addEmployeeToDepartment(Integer employeeId, Integer departmentId) {
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

		if (employeeOpt.isPresent() && departmentOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			Department department = departmentOpt.get();

			// Añade el departamento a la lista de departamentos del empleado
			employee.getDepartments().add(department);
			employeeRepository.save(employee); // Guarda el cambio en la base de datos

			return Optional.of(employee);
		}
		return Optional.empty();
	}

	public Optional<ProjectAssignment> assignToProjectWithRole(Integer employeeId, Integer projectId, Integer roleId) {
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		Optional<Project> projectOpt = projectRepository.findById(projectId);
		Optional<Role> roleOpt = roleRepository.findById(roleId);

		if (employeeOpt.isPresent() && projectOpt.isPresent() && roleOpt.isPresent()) {
			ProjectAssignment assignment = new ProjectAssignment();
			assignment.setEmployee(employeeOpt.get());
			assignment.setProject(projectOpt.get());
			assignment.setRole(roleOpt.get());

			return Optional.of(projectAssignmentRepository.save(assignment));
		}

		return Optional.empty();
	}

	// Obtener un employee por ID
	public Optional<Employee> getById(Integer id) {
		return employeeRepository.findById(id);
	}

	public Optional<Employee> getEmployeeWithSalaryById(Integer id) {
		return employeeRepository.findById(id);
	}

	// Actualizar un employee existente
	public Optional<Employee> update(Integer id, Employee employeeDetails) {
		Optional<Employee> optionalemployee = employeeRepository.findById(id);
		if (!optionalemployee.isPresent()) {
			return Optional.empty();
		}

		Employee employee = optionalemployee.get();

		// Actualiza otros campos según sea necesario
		// employee.setNombre(employeeDetails.getNombre());

		return Optional.of(employeeRepository.save(employee));
	}

	// Eliminar un employee por ID
	public boolean delete(Integer id) {
		if (!employeeRepository.existsById(id)) {
			return false;
		}
		employeeRepository.deleteById(id);
		return true;
	}
}
