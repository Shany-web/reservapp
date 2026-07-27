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

    public Cliente findByEmail(String email) {
        Cliente cli = cliRepo.findByEmail(email).orElseThrow(null);
        return cli;
    }

    public ClienteDTO mapToDTO(Cliente cli) {
        return ClienteDTO.builder().email(cli.getEmail())
                .telefono(cli.getTelefono())
                .nombre(cli.getNombre())
                .reservas(cli.getReservas()).build();

    }

    public Cliente mapToModel(ClienteDTO dto) {

        return Cliente.builder().email(dto.getEmail())
                .telefono(dto.getTelefono()).nombre(dto.getNombre())
                .reservas(dto.getReservas()).build();

    }

    public ClienteDTO saveClient(ClienteDTO cli) {

        return mapToDTO(cliRepo.save(mapToModel(cli)));
    }

    public void deleteClient(int id) {
        cliRepo.deleteById(id);
    }

    public List<Cliente> listClient() {
        return cliRepo.findAll();
    }

    public Cliente updateClient(ClienteDTO dto, int id) {
        Cliente cl = cliRepo.findById(id).orElse(null);

        if (cl == null) {
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        return cliRepo.save(mapToModel(dto));
    }

    public boolean existbyName(String name) {
        return cliRepo.existsByName(name);
    }

}
