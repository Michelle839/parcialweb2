package co.edu.ufps.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="employee")
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="first_name", length = 50)
	private String first_name;
	
	@Column(name="last_name", length = 50)
	private String last_name;
	
	@Column(name="birthdate")
	private Date birthdate;
	
	@Column(name="entry_date")
	private Date entry_date;
	
	@OneToMany(mappedBy = "employee", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Department> departments;
	
	@ManyToOne 
	@JoinColumn(name="dep_id")
	private Department department;
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name ="vist",
			joinColumns = @JoinColumn(name="department_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id")
			)
	List<Department> departamentos;
	
	@ManyToOne 
	@JoinColumn(name="pos_id")
	private Position position;
	
	@OneToMany(mappedBy = "employee", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<ProjectAssignment> projectAssignments;


}
