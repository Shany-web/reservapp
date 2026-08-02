package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

  public Especialidad findByNombre(String nombre);


}
