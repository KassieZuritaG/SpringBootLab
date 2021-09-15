package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "profesores")
public class Profesor extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_profesor")
	private Integer idprofesor;
		
	@Column(name = "titulo")
	private String titulo;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)

	@DateTimeFormat(pattern = "yyyy-MM-dd")	

	private Calendar fechaIngreso;

	@Column(name = "tipo_contrato")
	private String tipoContrato;

	@Column(name = "tipo_dedicacion")
	private String tiempoDedicacion;

	public Profesor() {
		super();
	}

	public Profesor(Integer id) {
		super();
		this.idprofesor = id;
	}	
	
	public Integer getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(Integer idprofesor) {
		this.idprofesor = idprofesor;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getTiempoDedicacion() {
		return tiempoDedicacion;
	}

	public void setTiempoDedicacion(String tiempoDedicacion) {
		this.tiempoDedicacion = tiempoDedicacion;
	}
	
	@OneToMany(mappedBy="docente", fetch=FetchType.LAZY)
	private List<Aula> aulas;
	
	@Override
	public String toString() {
		return super.toString();
	}
	

	public String fechaIng() {
		if(this.fechaIngreso == null) return "-";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaIngreso.getTime());
	}
	
	
}
