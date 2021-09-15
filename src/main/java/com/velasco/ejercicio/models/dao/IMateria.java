package com.velasco.ejercicio.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Materia;

public interface IMateria extends CrudRepository<Materia, Integer> {

	@Query("SELECT M FROM Materia M WHERE M.nivel = :n")	
	public List<Materia> findByNivel(Integer n);
	
	
}
