package com.velasco.ejercicio.models.services;

import java.util.List;

import com.velasco.ejercicio.models.entities.Participante;

public interface IParticipanteService {
	
	public void save(Participante a);
	public Participante findById(Integer id);
	public void delete(Integer id);
	public List<Participante> findAll();

}
