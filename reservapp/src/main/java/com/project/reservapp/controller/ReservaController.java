package com.project.reservapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.reservapp.DTO.ProfesionalDTO;
import com.project.reservapp.DTO.ReservaDTO;
import com.project.reservapp.service.ReserveService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/reserva")
public class ReservaController {
    @Autowired
    ReserveService service;

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findbyid(@PathVariable int id) {
        try {
            ReservaDTO reserv = service.findById(id);
            return new ResponseEntity<>(reserv, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findall() {
        List<ReservaDTO> reserva = service.findList();
        if (reserva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable int id) {
        String resultado = service.deleteReserve(id);
        if (resultado.contains("Reserva eliminada exitosamente")) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> saveReserva(@RequestBody ReservaDTO dto) {
        try {
            ReservaDTO reserva = service.SaveReserva(dto);
            return new ResponseEntity<>(reserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> updateReserva(@Valid @RequestBody ReservaDTO dto, int id) {
        try {
            ReservaDTO reserva = service.updateReserve(id, dto);
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
