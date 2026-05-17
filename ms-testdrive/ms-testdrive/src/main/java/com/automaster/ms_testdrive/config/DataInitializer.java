package com.automaster.ms_testdrive.config;
import com.automaster.ms_testdrive.model.TestDrive;
import com.automaster.ms_testdrive.repository.TestDriveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner{

    private final TestDriveRepository testDriveRepository;

    @Override
    public void run(String... args) throws Exception {

        if (testDriveRepository.count() == 0) {

            TestDrive testDrive1 = new TestDrive();
            testDrive1.setIdCliente(1L);
            testDrive1.setIdVehiculo(1L);
            testDrive1.setFechaTestDrive(LocalDateTime.now().plusDays(2));

            TestDrive testDrive2 = new TestDrive();
            testDrive2.setIdCliente(2L);
            testDrive2.setIdVehiculo(3L);
            testDrive2.setFechaTestDrive(LocalDateTime.now().plusDays(5));

            testDriveRepository.saveAll(Arrays.asList(testDrive1, testDrive2));

            log.info("Datos de prueba cargados en la base de datos de ms-testdrive");
        } else {
            log.info("La base de datos de ms-testdrive ya tiene registros");
        }
    }
}