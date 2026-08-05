package com.project.reservapp.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "servicio")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del servicioes obligatorio")
    @Size(min = 3, max = 25, message = "el nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 25, unique = true)
    private String nombre;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(nullable = false)
    private LocalTime duracion;

    @NotNull(message = "El precio es obligatario")
    @DecimalMin(value = "0.00", message = "El precio debe ser mayor o igual a zero")
    @Column(nullable = false)
    private double precio;

    @NotNull(message = "El precio es obligatario")
    @DecimalMin(value = "0.00", message = "El precio debe ser mayor o igual a zero")
    @Column(nullable = false)
    private double abono;

    @OneToMany(mappedBy = "servicio")
    @JsonManagedReference("Servicio-Reserva")
    private List<Reserva> reservas;

}
