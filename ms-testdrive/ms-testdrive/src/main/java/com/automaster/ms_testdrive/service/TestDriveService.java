package com.automaster.ms_testdrive.service;

import com.automaster.ms_testdrive.client.ClienteClient;
import com.automaster.ms_testdrive.client.StockClient;
import com.automaster.ms_testdrive.dto.TestDriveRequestDTO;
import com.automaster.ms_testdrive.dto.TestDriveResponseDTO;
import com.automaster.ms_testdrive.model.TestDrive;
import com.automaster.ms_testdrive.repository.TestDriveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestDriveService {

    private final TestDriveRepository testDriveRepository;

    public TestDriveResponseDTO agendarTestDrive(TestDriveRequestDTO request) {

        TestDrive testDrive = new TestDrive();
        testDrive.setIdCliente(request.getIdCliente());
        testDrive.setIdVehiculo(request.getIdVehiculo());
        testDrive.setFechaTestDrive(request.getFechaTestDrive());

        TestDrive guardado = testDriveRepository.save(testDrive);

        return convertirAResponseDTO(guardado);
    }

    public List<TestDriveResponseDTO> listarTodos() {
        List<TestDrive> testDrives = testDriveRepository.findAll();

        return testDrives.stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    private TestDriveResponseDTO convertirAResponseDTO(TestDrive testDrive) {
        return TestDriveResponseDTO.builder()
                .id(testDrive.getId())
                .idCliente(testDrive.getIdCliente())
                .idVehiculo(testDrive.getIdVehiculo())
                .fechaTestDrive(testDrive.getFechaTestDrive())
                .build();
    }
}