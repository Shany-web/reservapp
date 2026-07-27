# Base de Datos

## Tabla: clientes
- id (PK)
- nombre
- email
- telefono
- password_hash
- created_at

---

## Tabla: profesionales
- id (PK)
- nombre
- especialidad
- created_at

---

## Tabla: servicios
- id (PK)
- nombre
- descripcion
- duracion_minutos
- precio
- abono
- created_at

---

## Tabla: horarios
- id (PK)
- profesional_id (FK)
- fecha
- hora_inicio
- hora_fin
- estado (disponible / ocupado)

Relaciones:
- profesional_id → profesionales.id

---

## Tabla: reservas
- id (PK)
- cliente_id (FK)
- profesional_id (FK)
- servicio_id (FK)
- horario_id (FK)
- estado (pendiente / confirmada / cancelada)
- created_at

Relaciones:
- cliente_id → clientes.id
- profesional_id → profesionales.id
- servicio_id → servicios.id
- horario_id → horarios.id

---

## Tabla: pagos
- id (PK)
- reserva_id (FK)
- monto
- estado (pendiente / pagado / rechazado)
- metodo_pago
- created_at

Relaciones:
- reserva_id → reservas.id