package com.project.reservapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Optional<Cliente> findByEmail(String email);

    public boolean existsByNombre(String name);

}
