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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.reservapp.DTO.EspecialidadDTO;
import com.project.reservapp.service.EspecialidadService;

@Controller
@RequestMapping("/api/v1/especialidad")
public class EspecialidadController {
    @Autowired
    EspecialidadService service;

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listAll() {
        List<EspecialidadDTO> esp = service.findAll();
        if (esp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(esp, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> findById(@PathVariable int id) {
        try {
            EspecialidadDTO esp = service.findById(id);
            return new ResponseEntity<>(esp, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<EspecialidadDTO> saveEspecialidad(@RequestBody EspecialidadDTO dto) {
        try {
            EspecialidadDTO esp = service.savEspecialidad(dto);
            return new ResponseEntity<>(esp, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEsp(@PathVariable int id) {
        String resultado = service.deleteEspecialidad(id);
        if (resultado.contains("Especialidad eliminado exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
