package com.velasco.ejercicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.velasco.ejercicio.models.entities.Participante;

@Controller
@RequestMapping(value="/participante")  
public class ParticipanteController {	
	
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Participante participante = new Participante();
		model.addAttribute("participante", participante);
		return "participante/form"; 
	}
	
}
