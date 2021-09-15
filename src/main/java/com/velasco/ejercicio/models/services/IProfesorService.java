package com.velasco.ejercicio.models.services;

import java.util.List;

import com.velasco.ejercicio.models.entities.Profesor;

public interface IProfesorService {
	public void save(Profesor p);
	public Profesor findById(Integer id);
	public void delete(Integer id);
	public List<Profesor> findAll();
}
