package com.velasco.ejercicio.models.services;

import java.util.List;

import com.velasco.ejercicio.models.entities.Beca;

public interface IBecaService {
	public void save(Beca a);
	public Beca findById(Integer id);
	public void delete(Integer id);
	public List<Beca> findAll();
}
