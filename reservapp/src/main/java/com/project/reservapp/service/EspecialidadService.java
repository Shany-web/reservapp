package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.model.Especialidad;
import com.project.reservapp.repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    @Autowired
    EspecialidadRepository service;

    public Especialidad findById(int id) {
        return service.findById(id).orElseThrow(null);
    }

    public List<Especialidad> findAll() {
        return service.findAll();
    }

    public Especialidad findByname(String name) {
        return service.findByNombre(name);
    }

}
