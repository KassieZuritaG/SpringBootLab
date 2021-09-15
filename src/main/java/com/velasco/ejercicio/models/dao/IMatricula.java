package com.velasco.ejercicio.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Matricula;

public interface IMatricula extends CrudRepository<Matricula, Integer> {
		
	@Query("SELECT M FROM Matricula M WHERE M.estudiante.idalumno = :id")	
	public List<Matricula> findByEstudiante(Integer id);
	
	@Query("SELECT M FROM Matricula M WHERE M.curso.idmateria = :id")	
	public List<Matricula> findByMateria(Integer id);

}
