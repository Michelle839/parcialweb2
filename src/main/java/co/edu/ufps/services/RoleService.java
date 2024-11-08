package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Role;
import co.edu.ufps.repositories.RoleRepository;
@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> list() {
		return roleRepository.findAll();
	}
	
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	// Obtener un role por ID
	public Optional<Role> getById(Integer id) {
		return roleRepository.findById(id);
	}

	// Actualizar un role existente
	public Optional<Role> update(Integer id, Role roleDetails) {
		Optional<Role> optionalrole = roleRepository.findById(id);
		if (!optionalrole.isPresent()) {
			return Optional.empty();
		}

		Role role = optionalrole.get();

		// Actualiza otros campos según sea necesario
		//role.setNombre(roleDetails.getNombre());

		return Optional.of(roleRepository.save(role));
	}

	// Eliminar un role por ID
	public boolean delete(Integer id) {
		if (!roleRepository.existsById(id)) {
			return false;
		}
		roleRepository.deleteById(id);
		return true;
	}
}
