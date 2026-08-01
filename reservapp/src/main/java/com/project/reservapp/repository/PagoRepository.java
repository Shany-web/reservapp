package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Pago;

import java.util.List;
import com.project.reservapp.enums.EstadoPago;
import java.time.LocalDate;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    public List<Pago> findByMonto(double monto);

    public List<Pago> findByEstadoPago(EstadoPago estado);

    public List<Pago> findByFechaPago(LocalDate fechaPago);

    public Pago findByReserva(int reservaId);

}
