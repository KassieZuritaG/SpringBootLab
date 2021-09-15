package com.velasco.ejercicio.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="partipantes")
public class Participante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_participante")	
	private Integer idpartipante;
	
	@JoinColumn(name="fk_estudiante", referencedColumnName="pk_alumno")
	@ManyToOne
	private Alumno estudiante;
	
	@JoinColumn(name="fk_gira", referencedColumnName="pk_gira")
	@ManyToOne
	private Gira gira;
	
	@Column(name="tipo_financiamiento", length=1)
	private String tipo_financiamiento;
	
	@Column(name="valor")
	private Float valor;
	
	public Participante() {
		super();
	}
	
	public Participante(Integer id) {
		super();
		this.idpartipante = id;
	}

	public Integer getIdpartipante() {
		return idpartipante;
	}

	public void setIdpartipante(Integer idpartipante) {
		this.idpartipante = idpartipante;
	}

	public Alumno getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Alumno estudiante) {
		this.estudiante = estudiante;
	}

	public Gira getGira() {
		return gira;
	}

	public void setGira(Gira gira) {
		this.gira = gira;
	}

	public String getTipo_financiamiento() {
		return tipo_financiamiento;
	}

	public void setTipo_financiamiento(String tipo_financiamiento) {
		this.tipo_financiamiento = tipo_financiamiento;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	
	/**** TRANSIENT ***/
	
	@Transient
	private int estudianteid;
	
	@Transient
	private int giraid;
	
	public int getEstudianteid() {
		return estudianteid;
	}

	public void setEstudianteid(int estudianteid) {
		this.estudianteid = estudianteid;
	}

	public int getGiraid() {
		return giraid;
	}

	public void setGiraid(int id) {
		this.giraid = id;
	}

	
	
	
	

}
