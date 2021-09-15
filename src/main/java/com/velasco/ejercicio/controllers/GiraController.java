package com.velasco.ejercicio.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.velasco.ejercicio.models.entities.Alumno;
import com.velasco.ejercicio.models.entities.Gira;
import com.velasco.ejercicio.models.entities.Participante;
import com.velasco.ejercicio.models.entities.Profesor;
import com.velasco.ejercicio.models.services.IAlumnoService;
import com.velasco.ejercicio.models.services.IGiraService;
import com.velasco.ejercicio.models.services.IProfesorService;


@Controller
@SessionAttributes("Gira")
@RequestMapping(value = "/gira")
public class GiraController {

	@Autowired
	private IProfesorService srvprofesor;
	
	@Autowired
	private IGiraService srvGira;
	
	@Autowired
	private IAlumnoService srvAlumno;
	
	@GetMapping(value = "/create") // https://localhost:8080/usuarios/create
	public String create(Model model) {
		Gira gira = new Gira();		
		gira.setParticipantes(new ArrayList<Participante>());
		model.addAttribute("title","Registro de nueva gira");
		model.addAttribute("Gira",gira);
		List<Profesor> Profesor = srvprofesor.findAll();
		model.addAttribute("Profesor",Profesor);
		return "gira/form";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, Model model) {
		srvGira.delete(id);
		return "redirect:/gira/list";
	}
	
	@PostMapping(value = "/save") // https://localhost:8080/usuarios/save
	public String save(@Validated Gira gira, BindingResult result, Model model, @RequestParam("photo") MultipartFile image, SessionStatus status, RedirectAttributes flash, HttpSession session) {
		try {
			
			String message = "Gira agregada con exito";
			String titulo = "Registro de un nueva Gira";
			
			if(gira.getIdgira() != null) {
				message = "Gira actualizada con exito";
				titulo = "Actualizando Gira N°" + gira.getIdgira();
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title",titulo);
				model.addAttribute("error", "Error agregar gira");
				List<Profesor> Profesor = srvprofesor.findAll();
				model.addAttribute("Profesor",Profesor);
				return "gira/form";
			}
			
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos//gira");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					gira.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Gira giraSession = (Gira) session.getAttribute("Gira");
			for(Participante p : giraSession.getParticipantes()) {
				gira.getParticipantes().add(p);
			}
			
			srvGira.save(gira);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("success", ex.getMessage());
		}
		return "redirect:/gira/list";		
	}
	@GetMapping(value = "/update/{id}")
	public String update(@PathVariable(value = "id") Long id, Model model) {
		Gira gira = srvGira.findById(id);
		model.addAttribute("Gira", gira);
		model.addAttribute("title", "Actualizando Gira N�" + gira.getIdgira());
		List<Profesor> Profesor = srvprofesor.findAll();
		model.addAttribute("Profesor",Profesor);
		return "gira/form";
	}
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Gira> gira = srvGira.findAll();
		model.addAttribute("Gira", gira);
		model.addAttribute("title", "Listado de Giras");
		return "gira/list";
	}
	
	@GetMapping(value = "/retrieve/{id}")
	public String retrieve(@PathVariable(value = "id") Long id, Model model) {
		Gira gira = srvGira.findById(id);
		model.addAttribute("gira", gira);
		return "gira/card";
	}
	
	@PostMapping(value = "/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid Participante participante, 
			BindingResult result, Model model, HttpSession session) {				
		try {
			Alumno alumno = this.srvAlumno.findById(participante.getEstudianteid());			
			participante.setEstudiante(alumno);
			Gira gira = (Gira) session.getAttribute("Gira");
			gira.getParticipantes().add(participante);
			return participante;
		} catch (Exception ex) {			
			return ex;
		}		
	}
	
	@GetMapping(value = "/students")
	public String students(Model model, HttpSession session) {
		Gira gira = (Gira) session.getAttribute("Gira");
		model.addAttribute("participantes", gira.getParticipantes());		
		model.addAttribute("title", "Listado de participantes");
		return "participante/list";
	}
	
	
	
	
	
	
}
