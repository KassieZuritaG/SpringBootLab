package com.velasco.ejercicio.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.velasco.ejercicio.models.entities.Participante;

public interface IParticipante extends CrudRepository<Participante, Integer> {
}
