package com.project.reservapp.DTO;

import java.time.LocalDateTime;

import com.project.reservapp.enums.EstadoPago;
import com.project.reservapp.enums.MetodoPago;
import com.project.reservapp.model.Reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private double monto;
    private EstadoPago estado_pago;
    private MetodoPago metodo_pago;
    private LocalDateTime fechaPago;
    private Reserva reserva;
}
