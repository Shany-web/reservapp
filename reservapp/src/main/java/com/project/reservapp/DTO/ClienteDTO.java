package com.project.reservapp.DTO;

import java.util.List;

import com.project.reservapp.model.Reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
  private String nombre;
  private String telefono;
  private String email;
  private List<Reserva> reservas;

}
