package com.velasco.ejercicio.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.velasco.ejercicio.models.dao.IAlumno;
import com.velasco.ejercicio.models.entities.Alumno;

@Service
public class AlumnoService implements IAlumnoService {
	
	@Autowired //Inyección de dependencia
	private IAlumno dao;
		
	@Override
	@Transactional
	public void save(Alumno a) {
		dao.save(a);		
	}

	@Override
	@Transactional
	public Alumno findById(Integer id) {		
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);				
	}

	@Override
	@Transactional
	public List<Alumno> findAll() {		
		return (List<Alumno>) dao.findAll();
	}
	
	
	@Override
	@Transactional
	public List<Alumno> findByCedula(String cedula) {		
		return dao.findByCedulaStartingWith(cedula);
	}
}
