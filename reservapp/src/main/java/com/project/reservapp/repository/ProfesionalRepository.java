package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

}
