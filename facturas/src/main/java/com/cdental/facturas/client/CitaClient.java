package com.cdental.facturas.client;

import com.cdental.facturas.dto.CitaDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cita-client", url = "http://localhost:9080/api/citas")
public interface CitaClient {

    @GetMapping("/{id}")
    CitaDTO obtenerPorId(@PathVariable("id") Long id);
}
