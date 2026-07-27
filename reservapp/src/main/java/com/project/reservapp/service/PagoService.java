package com.project.reservapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.PagoDTO;
import com.project.reservapp.enums.EstadoPago;
import com.project.reservapp.model.Pago;
import com.project.reservapp.repository.PagoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepo;

    public Pago findByid(int id) {
        return pagoRepo.findById(id).orElseThrow(null);
    }

    public PagoDTO mapToDto(Pago pago) {
        return PagoDTO.builder().estado(pago.getEstado())
                .fechaPago(pago.getFechaPago())
                .metodo_pago(pago.getMetodo_pago()).monto(pago.getMonto())
                .reserva(pago.getReserva()).build();
    }

    public Pago maptoModel(PagoDTO dto) {
        return Pago.builder().estado(dto.getEstado())
                .fechaPago(dto.getFechaPago())
                .metodo_pago(dto.getMetodo_pago())
                .monto(dto.getMonto()).reserva(dto.getReserva()).build();
    }

    public PagoDTO SavePago(PagoDTO dto) {
        return mapToDto(pagoRepo.save(maptoModel(dto)));
    }

    /*
     * public Pago updatePago(int id, PagoDTO dto) {
     * Pago pago = pagoRepo.findById(id).orElseThrow();
     * if (pago == null) {
     * throw new EntityNotFoundException("Pago no encontrado");
     * }
     * return pagoRepo.save(maptoModel(dto));
     * 
     * }
     */

    public void DeletePago(int id) {
        Pago pago = pagoRepo.findById(id).orElseThrow();
        if (pago == null) {
            throw new EntityNotFoundException("Pago no encontrado");
        }
        pagoRepo.deleteById(id);
    }

    public List<Pago> findByMonto(double monto) {
        return pagoRepo.findByMonto(monto);

    }

    public List<Pago> findByestado(EstadoPago estado) {
        return pagoRepo.findByEstado(estado);
    }

    public List<Pago> findbyfecha(LocalDate fecha) {
        return pagoRepo.findByFechaPago(fecha);
    }

    public Pago findbyreserva(int reservaId) {
        return pagoRepo.findByReserva(reservaId);
    }

    public Pago updatePago(int idPago, double monto) {
        Pago pago = pagoRepo.findById(idPago)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));

        if (pago.getMonto() == monto) {
            pago.setEstado(EstadoPago.PAGADO);
            pago.setFechaPago(LocalDateTime.now());
            pagoRepo.save(pago);
        }
        return pago;
    }
}
