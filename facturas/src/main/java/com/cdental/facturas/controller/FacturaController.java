package com.cdental.facturas.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cdental.facturas.model.Factura;
import com.cdental.facturas.service.FacturaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<?> generarFactura(@RequestBody Factura factura) {
        try {
            Factura nuevaFactura = facturaService.emitirFactura(factura);
            return ResponseEntity.ok(nuevaFactura);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ingresos")
    public ResponseEntity<BigDecimal> obtenerIngresos(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        BigDecimal ingresos = facturaService.obtenerReporteIngresos(inicio, fin);
        return ResponseEntity.ok(ingresos);
    }

    @GetMapping
    public List<Factura> obtenerTodas() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        Factura factura = facturaService.buscarPorId(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> modificar(@PathVariable Long id, @RequestBody Factura factura) {
        Factura actualizada = facturaService.actualizarFactura(id, factura);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Factura factura = facturaService.buscarPorId(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/numero")
    public List<Factura> obtenerPorNumero(@RequestParam("numero") String numero) {
        return facturaService.buscarPorNumero(numero);
    }
}
