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

import co.edu.ufps.entities.Role;
import co.edu.ufps.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping
	public List<Role>  list() {
		
		return roleService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> getById(@PathVariable Integer id) {
		Optional<Role> Role = roleService.getById(id);
		return Role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Role create(@RequestBody Role Role) {
		return roleService.create(Role);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Role> update(@PathVariable Integer id, @RequestBody Role RoleDetails) {
		Optional<Role> updatedRole = roleService.update(id, RoleDetails);
		return updatedRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = roleService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	

}
