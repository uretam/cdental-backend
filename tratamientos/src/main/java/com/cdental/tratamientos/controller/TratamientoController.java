package com.cdental.tratamientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.cdental.tratamientos.model.Tratamiento;
import com.cdental.tratamientos.service.TratamientoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping
    public List<Tratamiento> obtenerTodos() {
        return tratamientoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtenerPorId(@PathVariable Long id) {
        Tratamiento tratamiento = tratamientoService.buscarPorId(id);
        if (tratamiento == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(tratamiento);
    }

    @PostMapping
    public Tratamiento crear(@RequestBody Tratamiento tratamiento) {
        return tratamientoService.guardar(tratamiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> modificar(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        Tratamiento actualizado = tratamientoService.actualizar(id, tratamiento);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tratamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
