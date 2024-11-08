package co.edu.ufps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="project_assignment")
@Data
public class ProjectAssignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	
	@ManyToOne 
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne 
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne 
	@JoinColumn(name="role_id")
	private Role role;
	
	
	
	
	
	
}
