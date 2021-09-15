package com.velasco.ejercicio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.ejercicio.models.dao.IGira;
import com.velasco.ejercicio.models.dao.IParticipante;
import com.velasco.ejercicio.models.entities.Gira;
import com.velasco.ejercicio.models.entities.Participante;

@Service
public class GiraService implements IGiraService{

	
	@Autowired //Inyeccion de dependencia
	private IGira dao;
	
	@Autowired //Inyeccion de dependencia
	private IParticipante daoParticipante;
		
	@Transactional
	public void save(Gira a) {		
		try {
			dao.save(a);
			for(Participante p : a.getParticipantes()) {
				p.setGira(a);
				this.daoParticipante.save(p);			
			}
		}
		catch(Exception ex) {
			throw ex;
		}
	}

	@Transactional
	public Gira findById(Long id) {
		return dao.findById(id).get();
	}

	@Transactional
	public void delete(Long id) {
		dao.deleteById(id);	
	}

	@Transactional
	public List<Gira> findAll() {
		return (List<Gira>)dao.findAll();
	}

}
