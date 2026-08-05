package com.project.reservapp.DTO;

import java.time.LocalTime;
import java.util.List;

import com.project.reservapp.model.Reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioDTO {
  private String nombre;
  private LocalTime duracion;
  private double precio;
  private double abono;
  private List<Reserva> reservas;

}
