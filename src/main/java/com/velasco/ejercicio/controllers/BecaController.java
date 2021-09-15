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

import com.velasco.ejercicio.models.entities.Beca;
import com.velasco.ejercicio.models.services.IBecaService;

@Controller
@SessionAttributes("beca")
@RequestMapping(value="/beca")  
public class BecaController {		
		@Autowired 
		private IBecaService srvBeca;
	
		@GetMapping(value="/create")
		public String create(Model model) {
			Beca beca = new Beca();
			model.addAttribute("title", "Nuevo registro de beca");
			model.addAttribute("beca", beca); 
			return "beca/form";
		}
		
		@GetMapping(value="/retrieve/{id}")
		public String retrieve(@PathVariable(value="id") Integer id, Model model) {
			Beca beca = srvBeca.findById(id);
			model.addAttribute("title", beca.toString());
			model.addAttribute("beca", beca);		
			return "beca/card";
		}
		
		@GetMapping(value="/update/{id}")
		public String update(@PathVariable(value="id") Integer id, Model model) {
			Beca beca = srvBeca.findById(id);
			model.addAttribute("beca", beca);
			model.addAttribute("title", "Actualizando beca " + beca);
			return "beca/form";
		}
		
		@GetMapping(value="/delete/{id}")
		public String delete(@PathVariable(value="id") Integer id, Model model) {
			srvBeca.delete(id);
			return "redirect:/beca/list";		
		}
		
		@GetMapping(value="/list")
		public String list(Model model) {
			List<Beca> becas = srvBeca.findAll();
			model.addAttribute("becas", becas);
			model.addAttribute("title", "Listado de becas");
			return "beca/list";
		}
		
		
		@PostMapping(value="/save") 
		public String save(@Validated Beca beca, BindingResult result, Model model,
				@RequestParam("photo") MultipartFile image,
				SessionStatus status, RedirectAttributes flash) {
			try {
				
				String message = "Beca creada correctamente";
				String titulo = "Nuevo registro de beca";
				
				if(beca.getIdBeca() != null) {
					message = "Beca actualizada correctamente";
					titulo = "Actualizando la beca " + beca;
				}
							
				if(result.hasErrors()) {
					model.addAttribute("title", titulo);
					return "beca/form";				
				}
				
				if (!image.isEmpty()) {				
					Path dir = Paths.get("src//main//resources//static//photos//becas");
					String rootPath = dir.toFile().getAbsolutePath();
					try {
						byte[] bytes = image.getBytes();
						Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
						Files.write(rutaCompleta, bytes);
						beca.setImagen(image.getOriginalFilename());

					} catch (IOException e) {
						e.printStackTrace();
					}
				}													
				srvBeca.save(beca);	
				status.setComplete();
				flash.addFlashAttribute("success", message);
			}
			catch(Exception ex) {
				flash.addFlashAttribute("error", ex.getMessage());
			}				
			return "redirect:/beca/list";
		}
}
