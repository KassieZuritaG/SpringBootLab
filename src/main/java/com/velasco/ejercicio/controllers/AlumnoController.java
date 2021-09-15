package com.velasco.ejercicio.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.ejercicio.models.entities.Alumno;
import com.velasco.ejercicio.models.services.IAlumnoService;

@Controller
@SessionAttributes("alumno")
@RequestMapping(value="/alumno")  
public class AlumnoController {	
	//Todas las peticiones que gestionar este controlador
	//empiezan por /alumno, p.e: https://localhost:8080/alumno/create
	
	@Autowired 
	private IAlumnoService srvAlumno;
	
	//Cada método en el controlador gestiona una petición al backend
	//a traves de una URL (puede ser -> 1. Escrita en el navegador
	//2. Puede sr Hyperlink, 3. Puede ser un action de un Form)
	
	@GetMapping(value="/create") //https://localhost:8080/alumno/create
	public String create(Model model) {
		Alumno alumno = new Alumno();
		alumno.setSexo("M");
		model.addAttribute("title", "Nuevo registro de alumn@");
		model.addAttribute("alumno", alumno); //similar al ViewBag // Se agrega a Session
		return "alumno/form"; //la ubicación de la vista
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Alumno alumno = srvAlumno.findById(id);
		model.addAttribute("title", alumno.toString());
		model.addAttribute("alumno", alumno);		
		return "alumno/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Alumno alumno = srvAlumno.findById(id);
		model.addAttribute("alumno", alumno);
		model.addAttribute("title", "Actualizando el registro de " + alumno);
		return "alumno/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvAlumno.delete(id);
		return "redirect:/alumno/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Alumno> alumnos = srvAlumno.findAll();
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("title", "Listado de alumnos");
		return "alumno/list";
	}
	
	
	@PostMapping(value="/save") 
	public String save(@Validated Alumno alumno, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Alumn@ agregado correctamente";
			String titulo = "Nuevo registro de alumn@";
			
			if(alumno.getIdalumno() != null) {
				message = "Alumn@ actualizado correctamente";
				titulo = "Actualizando el registro de " + alumno;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "alumno/form";				
			}
			
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					alumno.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}													
			srvAlumno.save(alumno);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/alumno/list";
	}
	
	@GetMapping(value="/search/{criteria}", produces="application/json")
	public @ResponseBody List<Alumno> search(@PathVariable(value="criteria") String criteria, Model model) {
		List<Alumno> lista = this.srvAlumno.findByCedula(criteria);		
		return lista;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
