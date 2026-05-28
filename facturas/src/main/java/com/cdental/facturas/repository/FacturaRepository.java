package com.cdental.facturas.repository;

import com.cdental.facturas.model.Factura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{

    @Query("SELECT SUM(f.montoTotal) FROM Factura f WHERE f.estado = 'PAID' AND f.fechaEmision BETWEEN :inicio AND :fin")
    BigDecimal sumarIngresosPorRango(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    List<Factura> findByNumeroFactura(String numeroFactura);
}
