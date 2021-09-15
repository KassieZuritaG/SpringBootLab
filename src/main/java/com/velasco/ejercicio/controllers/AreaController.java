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

import com.velasco.ejercicio.models.entities.Area;
import com.velasco.ejercicio.models.services.IAreaService;

@Controller
@SessionAttributes("area")
@RequestMapping(value="/area")  
public class AreaController {	
	//Todas las peticiones que gestionar este controlador
	//empiezan por /alumno, p.e: https://localhost:8080/area/create
	
	@Autowired 
	private IAreaService srvArea;
	
	//Cada método en el controlador gestiona una petición al backend
	//a traves de una URL (puede ser -> 1. Escrita en el navegador
	//2. Puede sr Hyperlink, 3. Puede ser un action de un Form)
	
	@GetMapping(value="/create") //https://localhost:8080/area/create
	public String create(Model model) {
		Area area = new Area();
		
		model.addAttribute("title", "Nuevo registro de área");
		model.addAttribute("area", area); //similar al ViewBag // Se agrega a Session
		return "area/form"; //la ubicación de la vista
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Area area = srvArea.findById(id);
		model.addAttribute("title", area.toString());
		model.addAttribute("area", area);		
		return "area/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Area area = srvArea.findById(id);
		model.addAttribute("area", area);
		model.addAttribute("title", "Actualizando el registro de " + area.getNombre());
		return "area/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			srvArea.delete(id);
			flash.addFlashAttribute("success", "El area fue eliminado con éxito.");
		}	
		catch(Exception ex) {
			flash.addFlashAttribute("error", "El area no pudo ser eliminado.");
		}
		
		return "redirect:/area/list";		
	}
	
	@GetMapping(value={"/","/list"})
	public String list(Model model) {
		List<Area> areas = srvArea.findAll();
		model.addAttribute("areas", areas);
		model.addAttribute("title", "Listado de áreas");
		return "area/list";
	}
	
	
	@PostMapping(value="/save") 
	public String save(@Validated Area area, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Área agregado correctamente";
			String titulo = "Nuevo registro de área";
			
			if(area.getIdarea() != null) {
				message = "Área actualizada correctamente";
				titulo = "Actualizando el registro de " + area;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "area/form";				
			}
			
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos//area");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					area.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}													
			srvArea.save(area);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/area/list";
	}	

}
