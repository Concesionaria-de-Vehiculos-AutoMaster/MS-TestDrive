package com.automaster.mstestdrive.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TestDriveRequestDTO {

    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rutCliente;

    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long idVehiculo;

    @NotBlank(message = "El RUT del vendedor asignado es obligatorio")
    private String rutVendedor;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La prueba de manejo debe programarse para una fecha futura")
    private LocalDateTime fechaHoraPrueba;
}