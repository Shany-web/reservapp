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
import com.project.reservapp.service.ProfesionalService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/profesional")
public class ProfesionalController {
    @Autowired
    ProfesionalService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalDTO> findbyid(@PathVariable int id) {
        try {
            ProfesionalDTO pro = service.findbyId(id);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProfesionalDTO>> findall() {
        List<ProfesionalDTO> pro = service.findall();
        if (pro.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProfesionalDTO>> findbyname(@PathVariable String nombre) {
        try {
            List<ProfesionalDTO> pro = service.findByname(nombre);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProfesional(@PathVariable int id) {
        String resultado = service.deleteProfesional(id);
        if (resultado.contains("Profesional eliminado exitosamente")) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<ProfesionalDTO> updateProfesional(@Valid @RequestBody ProfesionalDTO dto, int id) {
        try {
            ProfesionalDTO pro = service.updateprofesional(id, dto);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProfesionalDTO> saveProfesional(@RequestBody ProfesionalDTO dto) {
        try {
            ProfesionalDTO pro = service.saveProfesional(dto);
            return new ResponseEntity<>(pro, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Errorrrrrr " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
