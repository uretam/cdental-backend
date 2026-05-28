package com.cdental.citas.client;

import com.cdental.citas.dto.PacienteDTO;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paciente-client", url = "http://localhost:9080/api/pacientes")
public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteDTO obtenerPorId(@PathVariable("id") Long id);
}
