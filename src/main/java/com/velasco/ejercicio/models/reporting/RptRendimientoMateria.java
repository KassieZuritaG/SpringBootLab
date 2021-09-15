package com.velasco.ejercicio.models.reporting;

import java.io.Serializable;
import java.math.BigInteger;

public class RptRendimientoMateria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private BigInteger aprobados;
	private BigInteger reprobados;
	
	public RptRendimientoMateria(Integer id, String nombre, BigInteger aprobados, BigInteger reprobados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aprobados = aprobados;
	}

	public RptRendimientoMateria() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getAprobados() {
		return aprobados;
	}

	public void setAprobados(BigInteger aprobados) {
		this.aprobados = aprobados;
	}

	public BigInteger getReprobados() {
		return reprobados;
	}

	public void setReprobados(BigInteger reprobados) {
		this.reprobados = reprobados;
	}

	
	
	
	
	

}
