# API

## Base URL
/api

---

## Autenticación

### POST /auth/register
Registra un nuevo usuario.

**Body:**
{
  "nombre": "",
  "email": "",
  "password": ""
}

---

### POST /auth/login
Inicia sesión.

**Body:**
{
  "email": "",
  "password": ""
}

---

## Clientes

### GET /clientes/:id
Obtiene información de un cliente.

---

## Profesionales

### GET /profesionales
Lista todos los profesionales.

---

## Servicios

### GET /servicios
Lista los servicios disponibles.

---

## Horarios

### GET /horarios?profesional_id=1&fecha=2026-07-04
Obtiene horarios disponibles.

---

## Reservas

### POST /reservas
Crea una reserva.

**Body:**
{
  "cliente_id": 1,
  "profesional_id": 2,
  "servicio_id": 3,
  "horario_id": 10
}

---

### GET /reservas/:id
Obtiene detalles de una reserva.

---

### PATCH /reservas/:id/cancelar
Cancela una reserva.

---

## Pagos

### POST /pagos
Crea un pago de abono.

**Body:**
{
  "reserva_id": 1,
  "monto": 5000,
  "metodo": "tarjeta"
}
