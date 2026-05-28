package com.cdental.facturas.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    private String numeroFactura;
    private Long citaId;
    private Long pacienteId;
    private BigDecimal montoTotal;
    private String estado;
    private LocalDate fechaEmision;
}
