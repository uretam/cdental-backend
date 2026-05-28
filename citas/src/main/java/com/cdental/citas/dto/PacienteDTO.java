package com.cdental.citas.dto;

import lombok.Data;

@Data
public class PacienteDTO {
    private Long idPaciente;
    private String nombres;
    private String apellidos;
    private Boolean activo;
}
