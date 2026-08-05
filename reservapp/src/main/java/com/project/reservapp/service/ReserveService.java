package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.ReservaDTO;
import com.project.reservapp.model.Reserva;
import com.project.reservapp.repository.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReserveService {
    @Autowired
    ReservaRepository repo;

    public ReservaDTO findById(int id) {
        return mapToDTO(repo.findById(id).orElseThrow());
    }

    public List<ReservaDTO> findList() {
        return repo.findAll().stream().map(res -> mapToDTO(res)).toList();
    }

    public String deleteReserve(int id) {
        repo.deleteById(id);
        return "Reserva Eliminada exitosamnete";
    }

    public ReservaDTO mapToDTO(Reserva reserve) {
        return ReservaDTO.builder().cliente(reserve.getCliente()).estado(reserve.getEstado())
                .fechaCreacionReserva(reserve.getFechaCreacionReserva()).horario(reserve.getHorario())
                .pago(reserve.getPago()).profesional(reserve.getProfesional()).servicio(reserve.getServicio()).build();
    }

    public Reserva mapToModel(ReservaDTO dto) {
        return Reserva.builder().cliente(dto.getCliente()).estado(dto.getEstado())
                .fechaCreacionReserva(dto.getFechaCreacionReserva())
                .horario(dto.getHorario()).pago(dto.getPago()).profesional(dto.getProfesional())
                .servicio(dto.getServicio()).build();
    }

    public ReservaDTO SaveReserva(ReservaDTO dto) {
        return mapToDTO(repo.save(mapToModel(dto)));

    }

    public ReservaDTO updateReserve(int id, ReservaDTO res) {
        Reserva reser = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrado"));
        if (reser != null) {
            reser.setCliente(res.getCliente());
            reser.setEstado(res.getEstado());
            reser.setFechaCreacionReserva(res.getFechaCreacionReserva());
            reser.setHorario(res.getHorario());
            reser.setServicio(res.getServicio());
            reser.setProfesional(res.getProfesional());
            return mapToDTO(repo.save(reser));
        }

        return null;
    }

}
