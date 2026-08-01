package com.project.reservapp.DTO;

import java.sql.Date;
import java.time.LocalTime;

import com.project.reservapp.enums.EstadoHorario;
import com.project.reservapp.model.Profesional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDTO {

    private EstadoHorario estadoHorario;
    private Date fechaHorario;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Profesional profesional;

}
