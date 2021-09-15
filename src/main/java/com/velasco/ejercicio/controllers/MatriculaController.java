package com.velasco.ejercicio.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.velasco.ejercicio.models.entities.Alumno;
import com.velasco.ejercicio.models.entities.Materia;
import com.velasco.ejercicio.models.entities.Matricula;
import com.velasco.ejercicio.models.reporting.RptEstudiantesMateria;
import com.velasco.ejercicio.models.reporting.RptRendimientoMateria;
import com.velasco.ejercicio.models.services.IAlumnoService;
import com.velasco.ejercicio.models.services.IMateriaService;
import com.velasco.ejercicio.models.services.IMatriculaService;

@Controller
@SessionAttributes("matriculas")
@RequestMapping(value="/matricula")  
public class MatriculaController {	
		
	@Autowired 
	private IMatriculaService srvMatricula;
	
	@Autowired 
	private IMateriaService srvMateria;
	
	@Autowired 
	private IAlumnoService srvAlumno;
	
	@GetMapping(value="/create/{id}")
	public String create(@PathVariable(value="id") Integer id, Model model) {
		Matricula matricula = new Matricula();
		matricula.setEstudianteid(id);
		model.addAttribute("matricula", matricula);		
		return "matricula/form";
	}
	
	@GetMapping(value="/list/{id}")
	public String list(@PathVariable(value="id") Integer id, Model model) {
		List<Matricula> matriculas = this.srvMatricula.findByAlumno(id);
		model.addAttribute("matriculas", matriculas);		
		return "matricula/list";
	}

	@PostMapping(value = "/save")
	public String save(@RequestBody @Valid Matricula matricula, BindingResult result, Model model) {				
		try {
			Alumno alumno = this.srvAlumno.findById(matricula.getEstudianteid());
			Materia materia = this.srvMateria.findById(matricula.getCursoid());
			matricula.setEstudiante(alumno);
			matricula.setCurso(materia);
			this.srvMatricula.save(matricula);			
			return "matricula/list";
		} catch (Exception ex) {			
			return "matricula/form";
		}		
	}
	
	@GetMapping(value = "/rptMatriculadosMaterias")
	public String rptMatriculadosMateria(Model model) {
		return "matricula/rptMatriculadosMaterias";				
	}
	
	@GetMapping(value = "/rptRendimientoMaterias")
	public String rptRendimientoMateria(Model model) {
		List<Materia> materias = this.srvMateria.findAll();
		model.addAttribute("materias", materias);		
		return "matricula/rptRendimientoMaterias";				
	}
	
	@GetMapping(value = "/dataRptMatriculadosMaterias", produces="application/json")
	public @ResponseBody List<RptEstudiantesMateria> dataRptMatriculadosMateria(Model model) {				
		try {			
			return this.srvMatricula.rptMatriculadosMateria();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
	
	@GetMapping(value = "/dataRptRendimientoMateria/{id}", produces="application/json")
	public @ResponseBody RptRendimientoMateria dataRptRendimientoMateria(@PathVariable(value="id") Integer id, Model model) {				
		try {			
			return this.srvMatricula.rptRendimientoMateria(id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
	@GetMapping(value = "/listRptRendimientoMateria/{id}")
	public String listRptRendimientoMateria(@PathVariable(value="id") Integer id, Model model) {			
		List<Matricula> matriculas = this.srvMatricula.findByMateria(id);
		model.addAttribute("matriculas", matriculas);
		return "matricula/list";
	}
	
	
	
	
	
	
	

	
	
	
	
	

}
