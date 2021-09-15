package com.velasco.ejercicio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.ejercicio.models.dao.IParticipante;
import com.velasco.ejercicio.models.entities.Participante;

@Service
public class ParticipanteService implements IParticipanteService {
	
	@Autowired //Inyección de dependencia
	private IParticipante dao;
		
	@Override
	@Transactional
	public void save(Participante a) {
		dao.save(a);		
	}

	@Override
	@Transactional
	public Participante findById(Integer id) {		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);				
	}

	@Override
	@Transactional
	public List<Participante> findAll() {		
		return (List<Participante>) dao.findAll();
	}
}
