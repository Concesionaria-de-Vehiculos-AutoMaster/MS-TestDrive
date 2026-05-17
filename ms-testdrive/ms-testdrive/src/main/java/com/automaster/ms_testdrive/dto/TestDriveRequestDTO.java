package com.automaster.ms_testdrive.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestDriveRequestDTO {

    @NotNull(message = "El ID del cliente no puede estar vacío")
    private Long idCliente;

    @NotNull(message = "El ID del vehículo no puede estar vacío")
    private Long idVehiculo;

    @NotNull(message = "La fecha del test drive es obligatoria")
    @FutureOrPresent(message = "La fecha del test drive no puede ser en el pasado")
    private LocalDateTime fechaTestDrive;

}