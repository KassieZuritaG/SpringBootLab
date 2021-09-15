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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Code First que es 1ro codificar los modelos y a partir de ahi
//generar la base de datos

//javax.persistence es el ORM => JPA

@Entity  
@Table(name="materias")
public class Materia implements Serializable {
		
	@Override
	public String toString() {
		return nombre + " - " + nrc;
	}

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_materia")	
	private Integer idmateria;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nrc")
	private String nrc;
	
	@Column(name="creditos")
	private Integer creditos;

	@Column(name="nivel")
	private Integer nivel;
		
	public Materia() {
		super();
	}
	
	public Materia(Integer id) {
		super();
		this.idmateria = id;
	}

	public Integer getIdmateria() {
		return idmateria;
	}

	public void setIdmateria(Integer idmateria) {
		this.idmateria = idmateria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	
	
	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	/*====== Varios a uno con Area */
	@JoinColumn(name="fk_area", referencedColumnName="pk_area")
	@ManyToOne
	private Area area;	
	
	@JsonIgnore
	@OneToMany(mappedBy="asignatura", fetch=FetchType.LAZY)
	private List<Aula> aulas;
	
	@JoinColumn(name="fk_semestre", referencedColumnName="pk_semestre")
	@ManyToOne
	private Semestre semestre;

	@JsonIgnore
	@OneToMany(mappedBy="curso", fetch=FetchType.LAZY)
	private List<Matricula> matriculas;
		
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	

}
