# Reglas de Negocio

## Reservas

RN-001: Un horario solo puede ser reservado por un cliente.

RN-002: Una reserva queda en estado "Pendiente" hasta que se complete el pago del abono.

RN-003: Si el pago no se realiza dentro del tiempo establecido, la reserva se cancela automáticamente.

RN-004: Un cliente no puede reservar un horario que ya esté ocupado.

## Pagos

RN-005: El abono debe pagarse antes de confirmar la reserva.

RN-006: El monto del abono es definido por el negocio.

RN-007: Cada pago debe quedar asociado a una única reserva.

## Profesionales

RN-008: Cada profesional define sus horarios disponibles.

RN-009: Un profesional puede bloquear fechas o horarios.

## Clientes

RN-010: Un cliente debe estar registrado para realizar una reserva.

RN-011: Un cliente puede consultar el estado de sus reservas.

## Administración

RN-012: El administrador puede gestionar profesionales, servicios y reservas.

RN-013: El administrador puede cancelar una reserva si es necesario.