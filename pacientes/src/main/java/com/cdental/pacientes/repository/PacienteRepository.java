package com.cdental.pacientes.repository;

import com.cdental.pacientes.model.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	// save, saveAll, findById, getOne, findAll, delete, deleteById, deleteAll
}
