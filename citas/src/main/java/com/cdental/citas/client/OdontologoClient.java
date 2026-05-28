package com.cdental.citas.client;

import com.cdental.citas.dto.OdontologoDTO;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "odontologo-client", url = "http://localhost:9080/api/odontologos")
public interface OdontologoClient {

    @GetMapping("/{id}")
    OdontologoDTO obtenerPorId(@PathVariable("id") Long id);
}