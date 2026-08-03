package com.project.reservapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public PagoDTO findByid(int id) {
        return mapToDto(pagoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Pago no encontrado")));
    }

    public PagoDTO mapToDto(Pago pago) {
        return PagoDTO.builder().estado_pago(pago.getEstadoPago())
                .fechaPago(pago.getFechaPago())
                .metodo_pago(pago.getMetodo_pago()).monto(pago.getMonto())
                .reserva(pago.getReserva()).build();
    }

    public Pago maptoModel(PagoDTO dto) {
        return Pago.builder().estadoPago(dto.getEstado_pago())
                .fechaPago(dto.getFechaPago())
                .metodo_pago(dto.getMetodo_pago())
                .monto(dto.getMonto()).reserva(dto.getReserva()).build();
    }

    public PagoDTO SavePago(PagoDTO dto) {
        return mapToDto(pagoRepo.save(maptoModel(dto)));
    }

    public String DeletePago(int id) {
        Pago pago = pagoRepo.findById(id).orElseThrow(null);
        if (pago == null) {
            throw new EntityNotFoundException("Pago no encontrado");
        }
        pagoRepo.deleteById(id);
        return "Pago eliminado exitosamente";
    }

    public List<PagoDTO> findByMonto(double monto) {
        return pagoRepo.findByMonto(monto)
                .stream().map(this::mapToDto)
                .toList();

    }

    public List<PagoDTO> findByestado(EstadoPago estado) {
        return pagoRepo.findByEstadoPago(estado)
                .stream().map(this::mapToDto)
                .toList();
    }

    public List<PagoDTO> findbyfecha(LocalDate fecha) {
        return pagoRepo.findByFechaPago(fecha)
                .stream().map(this::mapToDto)
                .toList();
    }

    public PagoDTO findbyreserva(int reservaId) {
        return mapToDto(pagoRepo.findByReserva(reservaId));

    }

    public Pago updatePago(int idPago, double monto) {
        Pago pago = pagoRepo.findById(idPago)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));

        if (pago.getMonto() == monto) {
            pago.setEstadoPago(EstadoPago.PAGADO);
            pago.setFechaPago(LocalDateTime.now());
            pagoRepo.save(pago);
        }
        return pago;
    }

    public List<PagoDTO> findAll() {
        return pagoRepo.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }
}
