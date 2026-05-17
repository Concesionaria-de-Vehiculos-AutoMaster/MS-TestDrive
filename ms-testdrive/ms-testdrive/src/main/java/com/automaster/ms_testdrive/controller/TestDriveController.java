package com.automaster.ms_testdrive.controller;

import com.automaster.ms_testdrive.dto.TestDriveRequestDTO;
import com.automaster.ms_testdrive.dto.TestDriveResponseDTO;
import com.automaster.ms_testdrive.service.TestDriveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testdrives")
@RequiredArgsConstructor
public class TestDriveController {

    private final TestDriveService testDriveService;

    @PostMapping
    public ResponseEntity<TestDriveResponseDTO> agendarTestDrive(@Valid @RequestBody TestDriveRequestDTO request) {
        TestDriveResponseDTO response = testDriveService.agendarTestDrive(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TestDriveResponseDTO>> listarTestDrives() {
        List<TestDriveResponseDTO> lista = testDriveService.listarTodos();

        return ResponseEntity.ok(lista);
    }
}