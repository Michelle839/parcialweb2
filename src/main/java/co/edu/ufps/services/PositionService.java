package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Position;
import co.edu.ufps.repositories.PositionRepository;
@Service
public class PositionService {

	@Autowired
	PositionRepository positionRepository;
	
	public List<Position> list() {
		return positionRepository.findAll();
	}
	
	public Position create(Position position) {
		return positionRepository.save(position);
	}

	// Obtener un position por ID
	public Optional<Position> getById(Integer id) {
		return positionRepository.findById(id);
	}

	// Actualizar un position existente
	public Optional<Position> update(Integer id, Position positionDetails) {
		Optional<Position> optionalposition = positionRepository.findById(id);
		if (!optionalposition.isPresent()) {
			return Optional.empty();
		}

		Position position = optionalposition.get();

		// Actualiza otros campos según sea necesario
		//position.setNombre(positionDetails.getNombre());

		return Optional.of(positionRepository.save(position));
	}

	// Eliminar un position por ID
	public boolean delete(Integer id) {
		if (!positionRepository.existsById(id)) {
			return false;
		}
		positionRepository.deleteById(id);
		return true;
	}
}
