package com.project.reservapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.reservapp.DTO.PagoDTO;
import com.project.reservapp.enums.EstadoPago;
import com.project.reservapp.service.PagoService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/api/v1/pago")
public class PagoController {
  @Autowired
  PagoService service;

  @GetMapping("/{id}")
  public ResponseEntity<PagoDTO> findById(@PathVariable int id) {
    try {
      PagoDTO pago = service.findByid(id);
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<PagoDTO>> findAll() {
    List<PagoDTO> pago = service.findAll();
    if (pago.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(pago, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PagoDTO> savePago(@RequestBody PagoDTO dto) {
    try {
      PagoDTO pago = service.SavePago(dto);
      return new ResponseEntity<>(pago, HttpStatus.CREATED);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> DeletePago(@PathVariable int id) {
    String resultado = service.DeletePago(id);
    if (resultado.contains("Pago eliminado exitosamente")) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/monto/{monto}")
  public ResponseEntity<List<PagoDTO>> updatePago(@PathVariable double monto) {
    try {
      List<PagoDTO> pago = service.findByMonto(monto);
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/estado/{estado}")
  public ResponseEntity<List<PagoDTO>> findByMonto(@PathVariable EstadoPago estado) {
    try {
      List<PagoDTO> pago = service.findByestado(estado);
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/fecha/{fecha}")
  public ResponseEntity<List<PagoDTO>> findbyfecha(@PathVariable LocalDate fecha) {
    try {
      List<PagoDTO> pago = service.findbyfecha(fecha);
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/reserva/{id}")
  public ResponseEntity<PagoDTO> findbyreserva(@PathVariable int reservaId) {
    try {
      PagoDTO pago = service.findbyreserva(reservaId);
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
