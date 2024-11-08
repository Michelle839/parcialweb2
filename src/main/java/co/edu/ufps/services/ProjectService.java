package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Project;
import co.edu.ufps.repositories.ProjectRepository;
@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	public List<Project> list() {
		return projectRepository.findAll();
	}
	
	public Project create(Project project) {
		return projectRepository.save(project);
	}

	// Obtener un project por ID
	public Optional<Project> getById(Integer id) {
		return projectRepository.findById(id);
	}

	// Actualizar un project existente
	public Optional<Project> update(Integer id, Project projectDetails) {
		Optional<Project> optionalproject = projectRepository.findById(id);
		if (!optionalproject.isPresent()) {
			return Optional.empty();
		}

		Project project = optionalproject.get();

		// Actualiza otros campos según sea necesario
		//project.setNombre(projectDetails.getNombre());
		project.setName(projectDetails.getName());
		project.setDescripcion(projectDetails.getDescripcion());
		project.setStart_date(projectDetails.getStart_date());
		project.setEnd_date(projectDetails.getEnd_date());

		return Optional.of(projectRepository.save(project));
	}

	// Eliminar un project por ID
	public boolean delete(Integer id) {
		if (!projectRepository.existsById(id)) {
			return false;
		}
		projectRepository.deleteById(id);
		return true;
	}
}
