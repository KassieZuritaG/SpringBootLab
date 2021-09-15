package com.velasco.ejercicio.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByNombre(String nombre);	
		
}
