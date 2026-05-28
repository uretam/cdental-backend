package com.cdental.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.cdental.citas.dto.RegistrarCitaDTO;
import com.cdental.citas.model.Cita;
import com.cdental.citas.service.CitaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<?> crearCitaCompleta(@RequestBody RegistrarCitaDTO dto) {
        Cita nuevaCita = citaService.registrarCitaCompleta(dto);
        return ResponseEntity.ok(nuevaCita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Cita cita = citaService.buscarPorId(id);
        if (cita == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(cita);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cita> modificarCita(@PathVariable Long id, @RequestBody Cita cita) {
        Cita actualizada = citaService.actualizarCita(id, cita);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        boolean eliminado = citaService.eliminarCitaCompleta(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{idPaciente}/total")
    public ResponseEntity<Long> obtenerTotalCitasPorPaciente(@PathVariable Long idPaciente) {
        Long total = citaService.contarCitasDePaciente(idPaciente);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<List<Cita>> obtenerCitasPorEstado(@RequestParam("estado") String estado) {
        List<Cita> citas = citaService.buscarCitasPorEstado(estado);
        return ResponseEntity.ok(citas);
    }
}
