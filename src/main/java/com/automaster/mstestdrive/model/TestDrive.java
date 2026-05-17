package com.automaster.mstestdrive.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendas_testdrive")
@Data
public class TestDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private String rutCliente;

    @Column(nullable = false)
    private Long idVehiculo;

    @Column(nullable = false, length = 12)
    private String rutVendedor;

    @Column(nullable = false)
    private LocalDateTime fechaHoraPrueba;

    @Column(nullable = false, length = 20)
    private String estado; // "AGENDADO", "COMPLETADO", "CANCELADO"
}