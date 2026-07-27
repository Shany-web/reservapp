package com.project.reservapp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.reservapp.enums.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reserva")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "el estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @PastOrPresent(message = "La fecha de creacion no puede ser futura")
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacionReserva;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @OneToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;
}
