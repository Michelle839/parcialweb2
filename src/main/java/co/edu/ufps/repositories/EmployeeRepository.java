package co.edu.ufps.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	Optional<Employee> findById(Integer id);
}