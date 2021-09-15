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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.ejercicio.models.entities.Profesor;
import com.velasco.ejercicio.models.services.IProfesorService;

@Controller
@SessionAttributes("profesor")

@RequestMapping(value="/profesor")  
public class ProfesorController {	
	
	
	@Autowired 
	private IProfesorService srvProfesor;
	
	
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Profesor profesor = new Profesor();
		profesor.setSexo("M");

		model.addAttribute("title", "Nuevo registro de profesor/a");
		model.addAttribute("profesor", profesor);
		return "profesor/form"; 
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Profesor profesor = srvProfesor.findById(id);
		model.addAttribute("title", profesor.toString());
		model.addAttribute("profesor", profesor);		
		return "profesor/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Profesor profesor = srvProfesor.findById(id);
		model.addAttribute("profesor", profesor);

		model.addAttribute("title", "Actualizando el registro de " + profesor);
		return "profesor/form";
	}
	
	@GetMapping(value="/delete/{id}")

	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		
		try {
			srvProfesor.delete(id);
			flash.addFlashAttribute("success", "El registro fue eliminado con éxito.");
			
		} catch (Exception ex) {
			flash.addFlashAttribute("error", "El registro no pudo ser eliminado.");
		}
		
		return "redirect:/profesor/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Profesor> profesores = srvProfesor.findAll();
		model.addAttribute("profesores", profesores);
		model.addAttribute("title", "Listado de profesores");
		return "profesor/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Profesor profesor, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			

			String msg = "Profesor/a " + profesor.toString() +" agregado/a correctamente";
			String titulo = "Nuevo registro de Profesor/a";
			
			if(profesor.getIdprofesor() != null) {
				msg = "Profesor/a "+ profesor.toString()+ " actualizado/a correctamente";
				titulo = "Actualizando el registro de " + profesor;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);
				model.addAttribute("error","Error al registrar el/la profesor/a");
				return "profesor/form";				
			}
			
			if (!image.isEmpty()) {				

				Path dir = Paths.get("src//main//resources//static//photos//profesores");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					profesor.setImagen(image.getOriginalFilename());

				} catch (IOException e) {

					//e.printStackTrace();
					if(result.hasErrors()) {
						model.addAttribute("title", titulo);
						model.addAttribute("error","Error al cargar la foto");
						return "profesor/form";				
					}
				}
			}													
			srvProfesor.save(profesor);	
			status.setComplete();

			flash.addFlashAttribute("success", msg);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/profesor/list";
	}
	
	

}
