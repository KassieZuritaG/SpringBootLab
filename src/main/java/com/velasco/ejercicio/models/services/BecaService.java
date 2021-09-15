package com.velasco.ejercicio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.ejercicio.models.dao.IBeca;
import com.velasco.ejercicio.models.entities.Beca;
import com.velasco.ejercicio.models.services.IBecaService;

@Service
public class BecaService implements IBecaService {
	
	@Autowired
	private IBeca dao;
		
	@Override
	@Transactional
	public void save(Beca a) {
		dao.save(a);		
	}

	@Override
	@Transactional
	public Beca findById(Integer id) {		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);				
	}

	@Override
	@Transactional
	public List<Beca> findAll() {		
		return (List<Beca>) dao.findAll();
	}
}
