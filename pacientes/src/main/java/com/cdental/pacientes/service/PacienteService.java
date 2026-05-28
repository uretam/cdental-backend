package com.cdental.pacientes.service;

import com.cdental.pacientes.model.Paciente;
import com.cdental.pacientes.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente guardar(Paciente paciente) {
        paciente.setActivo(true);
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente datosNuevos) {
        Paciente pacienteExistente = pacienteRepository.findById(id).orElse(null);

        if (pacienteExistente != null) {
            pacienteExistente.setRut(datosNuevos.getRut());
            pacienteExistente.setNombres(datosNuevos.getNombres());
            pacienteExistente.setApellidos(datosNuevos.getApellidos());
            pacienteExistente.setFechaNacimiento(datosNuevos.getFechaNacimiento());
            pacienteExistente.setEmail(datosNuevos.getEmail());
            pacienteExistente.setTelefono(datosNuevos.getTelefono());
            pacienteExistente.setDireccion(datosNuevos.getDireccion());

            return pacienteRepository.save(pacienteExistente);
        }
        return null;
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
