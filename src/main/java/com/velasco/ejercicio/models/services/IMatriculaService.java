package com.velasco.ejercicio.models.services;

import java.util.List;

import com.velasco.ejercicio.models.entities.Matricula;
import com.velasco.ejercicio.models.reporting.RptEstudiantesMateria;
import com.velasco.ejercicio.models.reporting.RptRendimientoMateria;

public interface IMatriculaService {

	public void save(Matricula a);
	public Matricula findById(Integer id);
	public void delete(Integer id);
	public List<Matricula> findAll();
	public List<Matricula> findByAlumno(Integer id);
	public List<Matricula> findByMateria(Integer id);
	public List<RptEstudiantesMateria> rptMatriculadosMateria();
	public RptRendimientoMateria rptRendimientoMateria(Integer pr_materia);	
	
}
