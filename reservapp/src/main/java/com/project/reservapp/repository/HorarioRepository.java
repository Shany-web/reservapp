package com.project.reservapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.reservapp.model.Horario;
import java.util.List;
import com.project.reservapp.enums.EstadoHorario;
import java.time.LocalTime;



@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    public List<Horario> findByEstadoHorario(EstadoHorario estadoHorario);


}
