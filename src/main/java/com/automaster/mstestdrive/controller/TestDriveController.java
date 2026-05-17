package com.automaster.mstestdrive.controller;

import com.automaster.mstestdrive.dto.TestDriveRequestDTO;
import com.automaster.mstestdrive.dto.TestDriveResponseDTO;
import com.automaster.mstestdrive.service.TestDriveServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/testdrive")
public class TestDriveController {

    @Autowired
    private TestDriveServiceImpl testDriveService;

    @PostMapping("/agendar")
    public ResponseEntity<TestDriveResponseDTO> agendarTestDrive(@Valid @RequestBody TestDriveRequestDTO request) {
        log.info("Petición POST recibida para agendar Test Drive");
        TestDriveResponseDTO response = testDriveService.agendarPrueba(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}