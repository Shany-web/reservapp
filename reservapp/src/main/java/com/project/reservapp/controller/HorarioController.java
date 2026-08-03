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

import com.project.reservapp.DTO.HorarioDTO;
import com.project.reservapp.service.HorarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/horario")
public class HorarioController {
    @Autowired
    HorarioService service;

    @PostMapping
    public ResponseEntity<HorarioDTO> saveHo(@RequestBody HorarioDTO dto) {
        try {
            HorarioDTO ho = service.saveSchedule(dto);
            return new ResponseEntity<>(ho, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> findHorario(@PathVariable int id) {
        try {
            HorarioDTO ho = service.findById(id);
            if (ho == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(ho, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> listAll() {
        List<HorarioDTO> horario = service.listSchedule();
        if (horario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(horario, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<HorarioDTO> updateHorario(@Valid @RequestBody int id, HorarioDTO dto) {
        try {
            HorarioDTO ho = service.updateSchedule(id, dto);
            return new ResponseEntity<>(ho, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHorario(@PathVariable int id) {
        String resultado = service.deleteById(id);
        if (resultado.contains("Horario eliminado exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
