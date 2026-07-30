package com.project.reservapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reservapp.DTO.ClienteDTO;
import com.project.reservapp.model.Cliente;
import com.project.reservapp.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository cliRepo;

    public ClienteDTO findByEmail(String email) {
        return mapToDTO(cliRepo.findByEmail(email).orElseThrow(null));
    }

    public ClienteDTO mapToDTO(Cliente cli) {
        return ClienteDTO.builder().email(cli.getEmail())
                .telefono(cli.getTelefono())
                .nombre(cli.getNombre())
                .reservas(cli.getReservas()).build();

    }

    public ClienteDTO findById(int id) {
        return mapToDTO(cliRepo.findById(id).orElseThrow(null));
    }

    public Cliente mapToModel(ClienteDTO dto) {

        return Cliente.builder().email(dto.getEmail())
                .telefono(dto.getTelefono()).nombre(dto.getNombre())
                .reservas(dto.getReservas()).build();

    }

    public ClienteDTO saveClient(ClienteDTO cli) {

        return mapToDTO(cliRepo.save(mapToModel(cli)));
    }

    public String deleteClient(int id) {
        cliRepo.deleteById(id);
        return "Cliente Eliminado exitosamente";
    }

    public List<ClienteDTO> listClient() {
        return cliRepo.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ClienteDTO updateClient(ClienteDTO dto, int id) {
        Cliente cl = cliRepo.findById(id).orElse(null);

        if (cl == null) {
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        cl.setEmail(dto.getEmail());
        cl.setNombre(dto.getNombre());
        cl.setReservas(dto.getReservas());
        cl.setTelefono(dto.getTelefono());
        return mapToDTO(cliRepo.save(cl));
    }

    public boolean existbyName(String name) {
        return cliRepo.existsByName(name);
    }

}
