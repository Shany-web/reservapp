package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

}
