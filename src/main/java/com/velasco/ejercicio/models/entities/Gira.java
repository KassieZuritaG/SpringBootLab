package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
@Table(name="giras")
public class Gira implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_gira")
	private Long idgira;
	
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaInicio;
	
	@Column(name="fecha_fin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaFin;
	
	@Column(name="destino")
	@NotEmpty
	private String destino;
	
	/* Giras Varios a Uno con profesor */

	@JoinColumn(name="fk_profesor", referencedColumnName="pk_profesor")
	@ManyToOne
	private Profesor profesor;
	
	@Column(name="objetivo")
	@NotEmpty
	private String objetivo;
	
	@Column(name="imagen")
	private String imagen;
	
	@JsonIgnore
	@OneToMany(mappedBy="gira", fetch=FetchType.LAZY) 
	private List<Participante> participantes;
		
	public List<Participante> getParticipantes() {
		if(participantes == null)
			participantes = new ArrayList<Participante>();
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Long getIdgira() {
		return idgira;
	}

	public void setIdgira(Long idgira) {
		this.idgira = idgira;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String fechaInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(fechaInicio.getTime());
	}
	 
	public String fechaFin() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			return sdf.format(fechaFin.getTime());
	}

}
