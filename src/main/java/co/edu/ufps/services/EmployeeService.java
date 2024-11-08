package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Department;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.repositories.DepartmentRepository;
import co.edu.ufps.repositories.EmployeeRepository;
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> list() {
		return employeeRepository.findAll();
	}
	
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Autowired
    private DepartmentRepository departmentRepository;

    public Optional<Employee> addEmployeeToDepartment(Integer employeeId, Integer departmentId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

        if (employeeOpt.isPresent() && departmentOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Department department = departmentOpt.get();

            // Añade el departamento a la lista de departamentos del empleado
            employee.getDepartments().add(department);
            employeeRepository.save(employee);  // Guarda el cambio en la base de datos

            return Optional.of(employee);
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
		//employee.setNombre(employeeDetails.getNombre());

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
