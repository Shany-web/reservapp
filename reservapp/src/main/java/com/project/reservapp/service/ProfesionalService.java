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

    public ProfesionalDTO findbyId(int id) {
        return mapToDTO(repo.findById(id).orElseThrow(null));
    }

    public List<ProfesionalDTO> findByname(String nombre) {
        return repo.findByNombre(nombre).stream().map(prof -> mapToDTO(prof)).toList();

    }

    public ProfesionalDTO mapToDTO(Profesional dto) {
        return ProfesionalDTO.builder().email(dto.getEmail()).especialidades(dto.getEspecialidades())
                .horarios(dto.getHorarios()).nombre(dto.getNombre()).reservas(dto.getReservas()).build();

    }

    public Profesional mapToModel(ProfesionalDTO model) {
        return Profesional.builder().email(model.getEmail()).especialidades(model.getEspecialidades())
                .horarios(model.getHorarios()).nombre(model.getNombre()).reservas(model.getReservas()).build();
    }

    public ProfesionalDTO saveProfesional(ProfesionalDTO dto) {
        Profesional pro = new Profesional();
        pro.setEmail(dto.getEmail());
        pro.setNombre(dto.getNombre());
        return mapToDTO(repo.save(pro));
    }

    public String deleteProfesional(int id) {
        Profesional pro = repo.findById(id).orElseThrow(null);
        if (pro == null) {
            throw new EntityNotFoundException("Pago no encontrado");
        }
        repo.deleteById(id);
        return "Profesional eliminado exitosamente.";
    }

    public ProfesionalDTO updateprofesional(int id, ProfesionalDTO dto) {
        Profesional pro = repo.findById(id).orElseThrow(null);
        if (pro == null) {
            throw new EntityNotFoundException("Profesional no encontrado");
        }
        pro.setEmail(dto.getEmail());
        pro.setEspecialidades(dto.getEspecialidades());
        pro.setHorarios(dto.getHorarios());
        pro.setNombre(dto.getNombre());
        pro.setReservas(dto.getReservas());
        return mapToDTO(repo.save(pro));
    }

    public List<ProfesionalDTO> findall() {
        return repo.findAll().stream().map(pro -> mapToDTO(pro)).toList();
    }

}
