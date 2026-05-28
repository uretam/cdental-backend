package com.cdental.citas.dto;

import lombok.Data;

@Data
public class OdontologoDTO {
    private Long idOdontologo;
    private String nombres;
    private String apellidos;
    private Boolean activo;
}
