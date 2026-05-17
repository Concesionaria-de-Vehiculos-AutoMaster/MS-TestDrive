package com.automaster.mstestdrive.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TestDriveResponseDTO {
    private Long idAgenda;
    private String rutCliente;
    private Long idVehiculo;
    private String rutVendedor;
    private LocalDateTime fechaHoraPrueba;
    private String estado;
}