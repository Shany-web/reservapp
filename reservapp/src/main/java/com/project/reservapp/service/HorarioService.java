package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.HorarioDTO;
import com.project.reservapp.model.Horario;
import com.project.reservapp.repository.HorarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HorarioService {
    @Autowired
    HorarioRepository horarioRepo;

    public Horario findById(int id) {
        return horarioRepo.findById(id).orElseThrow(null);
    }

    public HorarioDTO mapToDTO(Horario ho) {
        return HorarioDTO.builder().estadoHorario(ho.getEstadoHorario())
                .horaInicio(ho.getHoraInicio())
                .horaFin(ho.getHoraFin()).fechaHorario(ho.getFechaHorario())
                .profesional(ho.getProfesional()).build();
    }

    public Horario mapToModel(HorarioDTO dto) {
        return Horario.builder().estadoHorario(dto.getEstadoHorario())
                .fechaHorario(dto.getFechaHorario())
                .horaFin(dto.getHoraFin()).horaInicio(dto.getHoraInicio())
                .profesional(dto.getProfesional()).build();
    }

    public List<Horario> listSchedule() {
        return horarioRepo.findAll();
    }

    public HorarioDTO saveSchedule(HorarioDTO dto) {
        return mapToDTO(horarioRepo.save(mapToModel(dto)));

    }

    public HorarioDTO updateSchedule(int id, HorarioDTO horario) {
        Horario ho = horarioRepo.findById(id).orElseThrow(null);
        if (ho == null) {
            throw new EntityNotFoundException("Horario no encontrado");
        }
        ho.setEstadoHorario(horario.getEstadoHorario());
        ho.setFechaHorario(horario.getFechaHorario());
        ho.setHoraFin(horario.getHoraFin());
        ho.setHoraInicio(horario.getHoraInicio());
        ho.setProfesional(horario.getProfesional());

        return mapToDTO(horarioRepo.save(ho));
    }

    public void deleteById(int id) {
        Horario ho = horarioRepo.findById(id).orElseThrow(null);
        if (ho == null) {
            throw new EntityNotFoundException("Horario no encontrado");
        }
        horarioRepo.deleteById(id);
    }

}
