package com.project.reservapp.DTO;

import java.time.LocalDateTime;

import com.project.reservapp.enums.EstadoReserva;
import com.project.reservapp.model.Cliente;
import com.project.reservapp.model.Horario;
import com.project.reservapp.model.Pago;
import com.project.reservapp.model.Profesional;
import com.project.reservapp.model.Servicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private EstadoReserva estado;
    private LocalDateTime fechaCreacionReserva;
    private Cliente cliente;
    private Profesional profesional;
    private Servicio servicio;
    private Horario horario;
    private Pago pago;

}
