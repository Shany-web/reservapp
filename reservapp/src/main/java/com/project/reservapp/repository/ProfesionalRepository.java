package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Profesional;
import java.util.List;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
    public List<Profesional> findByNombre(String nombre);

    public List<Profesional> findByEspecialidades(String especialidad);

}
