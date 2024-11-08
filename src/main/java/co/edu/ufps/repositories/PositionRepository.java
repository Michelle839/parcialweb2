package co.edu.ufps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Position;
import co.edu.ufps.entities.Project;


@Repository
public interface PositionRepository extends JpaRepository<Position,Integer>{
}