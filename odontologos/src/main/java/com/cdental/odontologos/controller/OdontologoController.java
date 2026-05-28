package com.cdental.odontologos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.cdental.odontologos.model.Odontologo;
import com.cdental.odontologos.service.OdontologoService;

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
@RequestMapping("/api/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> obtenerTodos() {
        return odontologoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> obtenerPorId(@PathVariable Long id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(odontologo);
    }

    @PostMapping
    public Odontologo crear(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> modificar(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        Odontologo actualizado = odontologoService.actualizar(id, odontologo);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
