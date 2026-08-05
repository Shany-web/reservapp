package com.project.reservapp.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.reservapp.enums.EstadoPago;
import com.project.reservapp.enums.MetodoPago;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "pago")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El monto es obligatario")
    @DecimalMin(value = "0.00", message = "El monto debe ser mayor o igual a zero")
    @Column(nullable = false)
    private double monto;

    @NotBlank(message = "el estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @NotNull(message = "El metodo de pago  es obligatorio")
    @Enumerated(EnumType.STRING)
    private MetodoPago metodo_pago;

    @PastOrPresent(message = "La fecha de pago no puede ser futura")
    @Column(nullable = true, name = "fecha_pago")
    private LocalDateTime fechaPago;

    @OneToOne(mappedBy = "pago")
    @JsonManagedReference("Pago-Reserva")
    private Reserva reserva;
}
