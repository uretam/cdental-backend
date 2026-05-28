package com.cdental.facturas.service;

import com.cdental.facturas.client.CitaClient;
import com.cdental.facturas.dto.CitaDTO;
import com.cdental.facturas.model.Factura;
import com.cdental.facturas.repository.FacturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private CitaClient citaClient;

    public Factura emitirFactura(Factura factura) {
        CitaDTO cita = citaClient.obtenerPorId(factura.getCitaId());
        if (cita == null) {
            throw new RuntimeException("Error: La cita número " + factura.getCitaId() + " no existe en el sistema.");
        }

        factura.setPacienteId(cita.getIdPaciente());
        factura.setMontoTotal(cita.getTotalGeneral());
        factura.setFechaEmision(LocalDate.now());
        factura.setEstado("PAID");

        return facturaRepository.save(factura);
    }

    public BigDecimal obtenerReporteIngresos(LocalDate inicio, LocalDate fin) {
        BigDecimal total = facturaRepository.sumarIngresosPorRango(inicio, fin);

        if (total == null) {
            return BigDecimal.ZERO;
        }

        return total;
    }

    public List<Factura> listarTodas() {
        return facturaRepository.findAll();
    }

    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura actualizarFactura(Long id, Factura datosNuevos) {
        Factura facturaExistente = facturaRepository.findById(id).orElse(null);
        
        if (facturaExistente != null) {
            facturaExistente.setEstado(datosNuevos.getEstado());
            facturaExistente.setNumeroFactura(datosNuevos.getNumeroFactura());
            
            return facturaRepository.save(facturaExistente);
        }
        return null;
    }

    public List<Factura> buscarPorNumero(String numero) {
        return facturaRepository.findByNumeroFactura(numero);
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
