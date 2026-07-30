package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.ProfesionalDTO;
import com.project.reservapp.model.Profesional;
import com.project.reservapp.repository.ProfesionalRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesionalService {

    @Autowired
    ProfesionalRepository repo;

    public Profesional findbyId(int id) {
        return repo.findById(id).orElseThrow(null);
    }

    public List<Profesional> findByname(String nombre) {
        return repo.findByName(nombre);
    }

    public ProfesionalDTO mapToDTO(Profesional dto) {
        return ProfesionalDTO.builder().email(dto.getEmail()).especialidades(dto.getEspecialidades())
                .horarios(dto.getHorarios()).nombre(dto.getNombre()).reservas(dto.getReservas()).build();

    }

    public Profesional mapToModel(ProfesionalDTO model) {
        return Profesional.builder().email(model.getEmail()).especialidades(model.getEspecialidades())
                .horarios(model.getHorarios()).nombre(model.getNombre()).reservas(model.getReservas()).build();
    }

    public ProfesionalDTO saveProfesional(int id) {
        Profesional pro = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
        return mapToDTO(pro);
    }

}
