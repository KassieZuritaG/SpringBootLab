package com.velasco.ejercicio.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Semestre;

public interface ISemestre extends CrudRepository<Semestre, Integer> {

}
