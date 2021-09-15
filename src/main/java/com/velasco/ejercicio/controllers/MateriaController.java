package com.velasco.ejercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.velasco.ejercicio.models.entities.Area;
import com.velasco.ejercicio.models.entities.Semestre;
import com.velasco.ejercicio.models.entities.Materia;
import com.velasco.ejercicio.models.services.IMateriaService;
import com.velasco.ejercicio.models.services.ISemestreService;
import com.velasco.ejercicio.models.services.IAreaService;

@Controller
@RequestMapping(value="/materia")
public class MateriaController {
	
	@Autowired 
	private IMateriaService srvMateria;
	
	@Autowired 
	private IAreaService srvArea;
	
	@Autowired 
	private ISemestreService srvSemestre;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Materia materia = new Materia();
		model.addAttribute("title", "Registro de nueva materia");
		model.addAttribute("materia", materia); 
		List<Area> areas = srvArea.findAll();
		model.addAttribute("areas", areas);		
		List<Semestre> semestres = srvSemestre.findAll();
		model.addAttribute("semestres", semestres);	
		return "materia/form"; 
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Materia materia = srvMateria.findById(id);
		model.addAttribute("materia", materia);				
		return "materia/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Materia materia = srvMateria.findById(id);
		model.addAttribute("materia", materia);
		model.addAttribute("title", "Actualizando el registro de " + materia);
		List<Area> areas = srvArea.findAll();
		model.addAttribute("areas", areas);
		List<Semestre> semestres = srvSemestre.findAll();
		model.addAttribute("semestres", semestres);	
		return "materia/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvMateria.delete(id);
		return "redirect:/materia/list";		
	}
	
	@GetMapping(value={"/","/list"})
	public String list(Model model) {
		List<Materia> materias = srvMateria.findAll();
		model.addAttribute("materias", materias);
		model.addAttribute("title", "Listado de materias");
		return "materia/list";
	}
	
	@GetMapping(value="/search/{criteria}", produces="application/json")
	public @ResponseBody List<Materia> search(@PathVariable(value="criteria") Integer criteria, 
			Model model) {
		List<Materia> list = srvMateria.findByNivel(criteria);		
		return list;		
	}
		
	@PostMapping(value="/save") 
	public String save(Materia materia, Model model) {
		srvMateria.save(materia);
		return "redirect:/materia/list";
	}
	
	
	
	

}















