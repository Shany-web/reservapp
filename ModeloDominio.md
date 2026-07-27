# Modelo de Dominio

## Objetos principales del sistema

### Cliente

Representa a la persona que reserva una hora.

- id
- nombre
- email
- teléfono

Relaciones:

- Un cliente puede tener muchas reservas.

---

### Profesional

Persona que ofrece el servicio.

- id
- nombre
- especialidad

Relaciones:

- Un profesional tiene muchos horarios.
- Un profesional tiene muchas reservas.

---

### Servicio

Tipo de atención que se ofrece.

- id
- nombre
- duración
- precio
- abono

Relaciones:

- Un servicio puede estar asociado a muchas reservas.

---

### Horario

Disponibilidad del profesional.

- id
- profesional_id
- fecha
- hora_inicio
- hora_fin
- estado (disponible / ocupado)

---

### Reserva

Representa la cita del cliente.

- id
- cliente_id
- profesional_id
- servicio_id
- horario_id
- estado (pendiente, confirmada, cancelada)
- fecha_creacion

Relaciones:

- Una reserva pertenece a un cliente.
- Una reserva pertenece a un profesional.
- Una reserva tiene un servicio.
- Una reserva tiene un horario.

---

### Pago

Registro del pago del abono.

- id
- reserva_id
- monto
- estado (pendiente, pagado, rechazado)
- fecha_pago
- metodo_pago

Relaciones:

- Un pago pertenece a una reserva.

---

## Resumen de relaciones

- Cliente → muchas Reservas
- Profesional → muchos Horarios y Reservas
- Servicio → muchas Reservas
- Reserva → 1 Cliente, 1 Profesional, 1 Servicio, 1 Horario
- Reserva → 1 Pago
