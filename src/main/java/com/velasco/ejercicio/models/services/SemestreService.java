package com.velasco.ejercicio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.ejercicio.models.dao.ISemestre;
import com.velasco.ejercicio.models.entities.Semestre;

@Service
public class SemestreService implements ISemestreService {

	@Autowired
	private ISemestre dao;

	@Override
	@Transactional
	public List<Semestre> findAll() {
		return (List<Semestre>) dao.findAll();
	}

}
