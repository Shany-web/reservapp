package com.project.reservapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.project.reservapp.DTO.ReservaDTO;
import com.project.reservapp.DTO.ServicioDTO;
import com.project.reservapp.model.Servicio;
import com.project.reservapp.service.ServicioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/servicio")
public class ServicioController {
    @Autowired
    ServicioService service;

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> findServicio(@PathVariable int id) {
        try {
            ServicioDTO serv = service.findbyid(id);
            return new ResponseEntity<>(serv, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ServicioDTO> findServiciobyName(@PathVariable String name) {
        try {
            ServicioDTO serv = service.findByNombre(name);
            return new ResponseEntity<>(serv, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServicio(@PathVariable int id) {
        String result = service.DeleteSevicio(id);
        if (result.contains("servicio eliminado exitosamente")) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> saveServicio(@RequestBody ServicioDTO dto) {
        try {
            ServicioDTO servicio = service.saveServicio(dto);
            return new ResponseEntity<>(servicio, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ServicioDTO> updateServicio(@Valid @RequestBody ServicioDTO dto, int id) {
        try {
            ServicioDTO servicio = service.updateServicio(id, dto);
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listService() {
        List<ServicioDTO> list = service.findall();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}