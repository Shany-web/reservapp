package com.project.reservapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.ServicioDTO;
import com.project.reservapp.model.Servicio;
import com.project.reservapp.repository.ServicioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository repo;

    public ServicioDTO findbyid(int id) {
        return mapToDTO(repo.findById(id).orElseThrow());
    }

    public ServicioDTO findByNombre(String nombre) {
        return mapToDTO(repo.findByNombre(nombre));
    }

    public ServicioDTO mapToDTO(Servicio serv) {
        return ServicioDTO.builder().abono(serv.getAbono())
                .duracion(serv.getDuracion()).nombre(serv.getNombre())
                .reservas(serv.getReservas()).precio(serv.getPrecio()).build();

    }

    public Servicio mapToModel(ServicioDTO serv) {
        return Servicio.builder().abono(serv.getAbono()).duracion(serv.getDuracion())
                .nombre(serv.getNombre()).precio(serv.getPrecio())
                .reservas(serv.getReservas())
                .build();
    }

    public List<ServicioDTO> findall() {
        return repo.findAll().stream().map(ser -> mapToDTO(ser)).toList();
    }

    public void deleteServicio(int id) {
        Servicio ser = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        repo.delete(ser);
    }

    public ServicioDTO saveServicio(ServicioDTO dto) {
        return mapToDTO(repo.save(mapToModel(dto)));

    }

    public ServicioDTO updateServicio(int id, ServicioDTO serv) {
        Servicio ser = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        if (ser != null) {
            ser.setAbono(serv.getAbono());
            ser.setDuracion(serv.getDuracion());
            ser.setNombre(serv.getNombre());
            ser.setPrecio(serv.getPrecio());
            ser.setReservas(serv.getReservas());
            return mapToDTO(repo.save(mapToModel(serv)));
        }
        return mapToDTO(repo.save(mapToModel(serv)));

    }
}