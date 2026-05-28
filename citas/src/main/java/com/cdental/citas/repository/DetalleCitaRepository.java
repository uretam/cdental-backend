package com.cdental.citas.repository;

import com.cdental.citas.model.DetalleCita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCitaRepository extends JpaRepository<DetalleCita, Long> {
    List<DetalleCita> findByIdCita(Long idCita);
}
