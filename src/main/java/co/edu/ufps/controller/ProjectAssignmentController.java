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
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.services.ProjectAssignmentService;

@RestController
@RequestMapping("/projectAssignments")
public class ProjectAssignmentController {
	@Autowired
	private ProjectAssignmentService projectAssignmentService;

	@GetMapping
	public List<ProjectAssignment>  list() {
		
		return projectAssignmentService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectAssignment> getById(@PathVariable Integer id) {
		Optional<ProjectAssignment> ProjectAssignment = projectAssignmentService.getById(id);
		return ProjectAssignment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ProjectAssignment create(@RequestBody ProjectAssignment ProjectAssignment) {
		return projectAssignmentService.create(ProjectAssignment);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectAssignment> update(@PathVariable Integer id, @RequestBody ProjectAssignment ProjectAssignmentDetails) {
		Optional<ProjectAssignment> updatedProjectAssignment = projectAssignmentService.update(id, ProjectAssignmentDetails);
		return updatedProjectAssignment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = projectAssignmentService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	

}
