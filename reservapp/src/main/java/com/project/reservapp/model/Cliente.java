package com.project.reservapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "El nombre del cliente es obligatorio")
	@Size(min = 8, max = 30, message = "El nombre debe tener entre 8 y 20 caracteres.")
	@Column(nullable = false, length = 30)
	private String nombre;

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "El formato del correo no es válido")
	@OneToMany(mappedBy = "reseva")
	@Size(max = 100, message = "El correo no puede superar los 100 caracteres")
	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank(message = "El teléfono es obligatorio para despachos")
	@Size(min = 9, max = 9, message = "Telefono debe tener 9 digitos")
	@Column(nullable = false, length = 9)
	private String telefono;

	@OneToMany(mappedBy = "cliente")
	private List<Reserva> reservas;

}
