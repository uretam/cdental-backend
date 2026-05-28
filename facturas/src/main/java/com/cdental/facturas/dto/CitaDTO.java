package com.cdental.facturas.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CitaDTO {
    private Long idCita;
    private Long idPaciente;
    private String estado;
    private BigDecimal totalGeneral;
}
