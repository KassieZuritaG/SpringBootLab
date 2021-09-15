package com.velasco.ejercicio.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Profesor;

public interface IProfesor extends CrudRepository<Profesor, Integer> {

}
