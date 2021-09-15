package com.velasco.ejercicio.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="roles", uniqueConstraints= { @UniqueConstraint(columnNames= {"fk_usuario", "nombre"}) })
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_rol")
	private Integer idrol;
	
	@Column(name="nombre")
	private String nombre;
	
	public Rol() {
		super();
	}
	
	public Rol(int id) {
		super();		
		this.setIdrol(id);
	}
	
	public Rol(String nom) {
		super();
		this.setNombre(nom);
	}

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
