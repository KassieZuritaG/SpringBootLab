package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
//import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="areas")
public class Area implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_area")	
	private Integer idarea;
	
	@Column(name="codigo")	
	private String codigo;
	
	@Column(name="nombre")	
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="coordinador")
	private String coordinador;
	
	@Column(name = "imagen")
	private String imagen;
	
	public Area() {
		super();
	}
	
	public Area(Integer id) {
		super();
		this.idarea = id;
	}
	
	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getIdarea() {
		return idarea;
	}

	public void setIdarea(Integer idarea) {
		this.idarea = idarea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.getCodigo()+" "+this.getNombre() +" "+this.getCoordinador();
	}
	
	
	
	
	/*====== Uno a varios con Materia */
	
	/*
	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
	//mappedBy debe ser un atributo en la clase relacionada
	@JsonIgnore
	@OneToMany(mappedBy="area", fetch=FetchType.LAZY) 
	private List<Materia> materias;	*/
	
	
}
