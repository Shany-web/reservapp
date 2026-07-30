package com.project.reservapp.DTO;

import java.util.List;

import com.project.reservapp.model.Especialidad;
import com.project.reservapp.model.Horario;
import com.project.reservapp.model.Reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalDTO {
    private String nombre;
    private String email;
    private List<Especialidad> especialidades;
    private List<Reserva> reservas;
    private List<Horario> horarios;

}
