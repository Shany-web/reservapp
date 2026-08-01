package com.project.reservapp.controller;

import com.project.reservapp.repository.ClienteRepository;
import java.util.List;

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

import com.project.reservapp.DTO.ClienteDTO;
import com.project.reservapp.model.Cliente;
import com.project.reservapp.service.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;
    @Autowired
    private ClienteService cli;

    ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> allClients() {
        List<ClienteDTO> clients = cli.listClient();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable int id) {
        try {
            ClienteDTO cliente = cli.findById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> getBYEmail(@PathVariable String email) {
        try {
            ClienteDTO client = cli.findByEmail(email);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> SaveClient(@RequestBody ClienteDTO dto) {
        try {
            ClienteDTO save = cli.saveClient(dto);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateClient(@Valid @RequestBody ClienteDTO dto, @PathVariable int id) {
        try {
            ClienteDTO client = cli.updateClient(dto, id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id) {
        String resultado = cli.deleteClient(id);

        if (resultado.contains("Cliente eliminado exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
