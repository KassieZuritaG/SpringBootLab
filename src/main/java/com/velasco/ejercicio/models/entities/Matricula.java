package com.velasco.ejercicio.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="matriculas")
public class Matricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_matricula")	
	private Integer idmatricula;
	
	@Column(name="fecha")
	private Calendar fecha;
	
	@Column(name="costo")
	private Float costo;
	
	@Column(name="tipo", length=1)
	private String tipo;
	
	@Column(name="promedio")
	private Float promedio;
	
	@Column(name = "creado_en")
	private LocalDateTime creadoEn;

	@Column(name = "creado_por")
	private String creadoPor;

	@Column(name = "modificado_en")
	private LocalDateTime modificadoEn;

	@Column(name = "modificado_por")
	private String modificadoPor;
		
	/**** TRANSIENT INICIO ***/
	/** Los atributos que tienen la anotación @Transient no transaccionan con
	 la BDD, se esta utilizando a modo de artificio para emular las relaciones
	 entre Matrícula - Alumno; y Matrícula - Materia **/
	
	@Transient
	private int estudianteid;
	
	@Transient
	private int cursoid;
	
	public int getEstudianteid() {
		return estudianteid;
	}

	public void setEstudianteid(int estudianteid) {
		this.estudianteid = estudianteid;
	}

	public int getCursoid() {
		return cursoid;
	}

	public void setCursoid(int cursoid) {
		this.cursoid = cursoid;
	}

	/**** TRANSIENT FIN***/

	
	@JoinColumn(name="fk_estudiante", referencedColumnName="pk_alumno")
	@ManyToOne
	private Alumno estudiante;
	
	@JoinColumn(name="fk_curso", referencedColumnName="pk_materia")
	@ManyToOne
	private Materia curso;
	
	public Matricula() {
		super();
	}
	
	public Matricula(Integer id) {
		super();
		this.idmatricula = id;
	}

	public Integer getIdmatricula() {
		return idmatricula;
	}

	public void setIdmatricula(Integer idmatricula) {
		this.idmatricula = idmatricula;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	
	public Float getPromedio() {
		return promedio;
	}

	public void setPromedio(Float promedio) {
		this.promedio = promedio;
	}

	public Alumno getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Alumno estudiante) {
		this.estudiante = estudiante;
	}

	public Materia getCurso() {
		return curso;
	}

	public void setCurso(Materia curso) {
		this.curso = curso;
	}
	
	
	public String tipoDesc() {
		switch(this.tipo) {
			case "P" : 
				return "Primera";
			case "S":
				return "Segunda";
		}
		return "Tercera";
	}
	

	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public LocalDateTime getModificadoEn() {
		return modificadoEn;
	}

	public void setModificadoEn(LocalDateTime modificadoEn) {
		this.modificadoEn = modificadoEn;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	
	@PrePersist
	public void prePersist() {
		creadoEn = LocalDateTime.now();
		SecurityContext context = SecurityContextHolder.getContext();
        creadoPor = context.getAuthentication().getName();
	}

	@PreUpdate
	public void preUpdate() {
		modificadoEn = LocalDateTime.now();
		SecurityContext context = SecurityContextHolder.getContext();
        modificadoPor = context.getAuthentication().getName();
	}
	
	
	
	
		
	
	
	
	
	

}
