package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="alumnos")
public class Alumno extends Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_alumno")
	private Integer idalumno;
		
	@Column(name="identificador")
	@NotEmpty
	@Size(max=10)
	private String identificador;

	@JsonIgnore
	@OneToMany(mappedBy="estudiante", fetch=FetchType.LAZY)
	private List<Matricula> matriculas;
	
	@JsonIgnore
	@OneToMany(mappedBy="estudiante", fetch=FetchType.LAZY) 
	private List<Participante> participantes;
	
	
	public Alumno() {
		super();
	}
	
	public Alumno(Integer id) {
		super();
		this.idalumno = id;
	}

	
	
	
	
	
	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Integer getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
	

}
