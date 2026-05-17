package com.automaster.ms_testdrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name = "testdrives")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_vehiculo", nullable = false)
    private Long idVehiculo;

    @Column(name = "fecha_test_drive", nullable = false)
    private LocalDateTime fechaTestDrive;

}
