package com.velasco.ejercicio.models.services;

import java.util.List;

import com.velasco.ejercicio.models.entities.Gira;

public interface IGiraService {

	public void save(Gira a);
	public Gira findById(Long id);
	public void delete(Long id);
	public List<Gira> findAll();
}
