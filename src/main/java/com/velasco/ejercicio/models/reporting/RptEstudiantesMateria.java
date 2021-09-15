package com.velasco.ejercicio.models.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptEstudiantesMateria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	private String nombre;
	private BigInteger estudiantes;
	
	public RptEstudiantesMateria(BigInteger id, String nombre, BigInteger matriculados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estudiantes = matriculados;
	}

	public RptEstudiantesMateria() {
		super();
	}
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(BigInteger matriculados) {
		this.estudiantes = matriculados;
	}
	
	
	
	

}
