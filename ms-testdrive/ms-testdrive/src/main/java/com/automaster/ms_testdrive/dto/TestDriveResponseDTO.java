package com.automaster.ms_testdrive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDriveResponseDTO {

    private Long id;
    private Long idCliente;
    private Long idVehiculo;
    private LocalDateTime fechaTestDrive;
}
