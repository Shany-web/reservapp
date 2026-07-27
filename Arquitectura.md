# Arquitectura del Sistema

## Tipo de arquitectura
Arquitectura cliente-servidor con API REST.

---

## Componentes

### 1. Frontend (Web + Mobile)
- Aplicación web (React o Flutter Web)
- Aplicación móvil (Flutter)
- Consume la API

Responsabilidad:
- Mostrar interfaz
- Enviar solicitudes al backend
- Mostrar datos al usuario

---

### 2. Backend (API)
- Node.js / NestJS / Supabase Functions (opcional)
- Expone endpoints REST

Responsabilidad:
- Lógica de negocio
- Validación de reglas
- Gestión de reservas y pagos

---

### 3. Base de datos
- PostgreSQL

Responsabilidad:
- Guardar usuarios, reservas, pagos, horarios

---

### 4. Servicio de pagos
- Pasarela de pago (ej: Mercado Pago o Transbank)

Responsabilidad:
- Procesar pagos de abono
- Confirmar transacciones

---

## Flujo del sistema

Cliente (App)
   ↓
Frontend (Flutter/Web)
   ↓
API Backend
   ↓
Base de datos / Pagos
   ↓
Respuesta al frontend
   ↓
Cliente ve resultado

---

## Diagrama simplificado

[Cliente]
   ↓
[App Web/Móvil]
   ↓
[API Backend]
   ↓
[PostgreSQL] + [Pasarela de Pago]