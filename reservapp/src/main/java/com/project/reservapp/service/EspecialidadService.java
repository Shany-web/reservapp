package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.EspecialidadDTO;
import com.project.reservapp.model.Especialidad;
import com.project.reservapp.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    @Autowired
    EspecialidadRepository repo;

    public EspecialidadDTO findById(int id) {
        return mapToDTO(repo.findById(id).orElseThrow(null));
    }

    public EspecialidadDTO mapToDTO(Especialidad espe) {
        return EspecialidadDTO.builder().nombre(espe.getNombre()).build();
    }

    public Especialidad mapToModel(EspecialidadDTO dto) {
        return Especialidad.builder().nombre(dto.getNombre()).build();
    }

    public List<EspecialidadDTO> findAll() {
        return repo.findAll().stream().map(es -> mapToDTO(es)).toList();
    }

    public EspecialidadDTO findByname(String name) {
        return mapToDTO(repo.findByNombre(name));
    }

    public String deleteEspecialidad(int id) {
        repo.deleteById(id);
        return "Especialidad eliminado exitosamente.";
    }

    public EspecialidadDTO savEspecialidad(EspecialidadDTO dto) {
        return mapToDTO(repo.save(mapToModel(dto)));

    }
}
