package com.velasco.ejercicio.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Alumno;

public interface IAlumno extends CrudRepository<Alumno, Integer> {
	
	public List<Alumno> findByCedulaStartingWith(String cedula);
	
}
