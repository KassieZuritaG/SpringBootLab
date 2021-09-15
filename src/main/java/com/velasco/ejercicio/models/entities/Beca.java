package com.velasco.ejercicio.models.entities;


import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="becas")
public class Beca implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_beca")	
	private Integer idBeca;
	
	
	@Column(name="nombre")
	@NotEmpty
	@Size(max=60)
	private String nombre;
	
	@Column(name="monto")
	@DecimalMin("1.00") 
	@DecimalMax("5000.00") 
	private float monto;
	
	@Column(name="descripcion")
	@NotEmpty
	@Size(max=60)
	private String descripcion;
	
	@Column(name="nota_minima")
	@DecimalMin("1.00") 
	private float notaMinima;
	
	@Column(name = "imagen")
	private String imagen;
	

	public Beca() {
		super();
	}
	
	public Beca(Integer idBeca) {
		super();
		this.idBeca = idBeca;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Integer getIdBeca() {
		return idBeca;
	}

	public void setIdBeca(Integer idBeca) {
		this.idBeca = idBeca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getMonto() {
		return (float) monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getNotaMinima() {
		return notaMinima;
	}

	public void setNotaMinima(Float notaMinima) {
		this.notaMinima = notaMinima;
	}

	@Override
	public String toString() {
		return "Beca " + nombre;
	}
	
	
	

}
