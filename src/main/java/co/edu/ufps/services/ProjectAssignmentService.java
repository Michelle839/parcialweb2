package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
@Service
public class ProjectAssignmentService {

	@Autowired
	ProjectAssignmentRepository projectAssignmentRepository;
	
	public List<ProjectAssignment> list() {
		return projectAssignmentRepository.findAll();
	}
	
	public ProjectAssignment create(ProjectAssignment projectAssignment) {
		return projectAssignmentRepository.save(projectAssignment);
	}

	// Obtener un projectAssignment por ID
	public Optional<ProjectAssignment> getById(Integer id) {
		return projectAssignmentRepository.findById(id);
	}

	// Actualizar un projectAssignment existente
	public Optional<ProjectAssignment> update(Integer id, ProjectAssignment projectAssignmentDetails) {
		Optional<ProjectAssignment> optionalprojectAssignment = projectAssignmentRepository.findById(id);
		if (!optionalprojectAssignment.isPresent()) {
			return Optional.empty();
		}

		ProjectAssignment projectAssignment = optionalprojectAssignment.get();

		// Actualiza otros campos según sea necesario
		//projectAssignment.setNombre(projectAssignmentDetails.getNombre());

		return Optional.of(projectAssignmentRepository.save(projectAssignment));
	}

	// Eliminar un projectAssignment por ID
	public boolean delete(Integer id) {
		if (!projectAssignmentRepository.existsById(id)) {
			return false;
		}
		projectAssignmentRepository.deleteById(id);
		return true;
	}
}
