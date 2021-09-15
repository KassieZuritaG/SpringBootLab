package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_usuario")
	private Integer idusuario;
	
	@Column(name="nombre", unique=true)
	@NotEmpty
	@Size(min=4)
	private String nombre;
	
	@Column(name="password")
	@NotEmpty
	@Size(min=8)
	private String password;
	
	@Column(name="habilitado")
	private boolean habilitado;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name="fk_usuario")
	private List<Rol> roles;
			
	public List<Rol> getRoles() {
		if(roles == null)
			roles = new ArrayList<>();
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Usuario() {
		super();
	}
	
	public Usuario(int id) {
		super();
		this.setIdusuario(id);
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	public boolean getHabilitado() {
		return this.habilitado;
	}
	
	public void setHabilitado(boolean h) {
		this.habilitado = h;
	}
		

}
