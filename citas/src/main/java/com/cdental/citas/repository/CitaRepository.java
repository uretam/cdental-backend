package com.cdental.citas.repository;

import com.cdental.citas.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    Long countByIdPaciente(Long idPaciente);

    List<Cita> findByEstado(String estado);
}
