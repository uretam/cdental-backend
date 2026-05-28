package com.cdental.citas.dto;

import com.cdental.citas.model.Cita;
import com.cdental.citas.model.DetalleCita;

import lombok.Data;
import java.util.List;

@Data
public class RegistrarCitaDTO {
    private Cita cita;
    private List<DetalleCita> detalles;
}
