package com.automaster.mstestdrive.service;

import com.automaster.mstestdrive.dto.TestDriveRequestDTO;
import com.automaster.mstestdrive.dto.TestDriveResponseDTO;
import com.automaster.mstestdrive.model.TestDrive;
import com.automaster.mstestdrive.repository.TestDriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class TestDriveServiceImpl {

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public TestDriveResponseDTO agendarPrueba(TestDriveRequestDTO request) {
        log.info("Iniciando agendamiento de Test Drive para cliente {} y vehículo {}", request.getRutCliente(), request.getIdVehiculo());

        // 1. Validar Cliente
        validarCliente(request.getRutCliente());

        // 2. Validar Vehículo
        validarVehiculo(request.getIdVehiculo());

        // 3. NUEVO: Validar que el vendedor exista en MS-Vendedores (Puerto 8082)
        validarVendedor(request.getRutVendedor());

        // 4. Guardar
        TestDrive agenda = new TestDrive();
        agenda.setRutCliente(request.getRutCliente());
        agenda.setIdVehiculo(request.getIdVehiculo());
        agenda.setRutVendedor(request.getRutVendedor()); // Guardamos al vendedor
        agenda.setFechaHoraPrueba(request.getFechaHoraPrueba());
        agenda.setEstado("AGENDADO");

        TestDrive guardado = testDriveRepository.save(agenda);
        log.info("Test Drive agendado exitosamente con ID: {}", guardado.getId());

        return mapearADTO(guardado);
    }

    // --- MÉTODOS PRIVADOS DE WEBCLIENT ---

    private void validarCliente(String rut) {
        log.info("Consultando MS-Clientes para verificar RUT: {}", rut);
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/clientes/rut/" + rut)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Cliente con RUT {} no encontrado en el CRM", rut);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente no está registrado. Debe registrarse primero.");
        } catch (Exception ex) {
            log.error("Error de comunicación con MS-Clientes: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servicio de clientes no disponible");
        }
    }

    private void validarVehiculo(Long idVehiculo) {
        log.info("Consultando MS-Stock para verificar disponibilidad del vehículo ID: {}", idVehiculo);
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/api/stock/" + idVehiculo)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Vehículo ID {} no encontrado en stock", idVehiculo);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vehículo seleccionado no existe en el inventario.");
        } catch (Exception ex) {
            log.error("Error de comunicación con MS-Stock: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servicio de inventario no disponible");
        }
    }

    private TestDriveResponseDTO mapearADTO(TestDrive testDrive) {
        TestDriveResponseDTO dto = new TestDriveResponseDTO();
        dto.setIdAgenda(testDrive.getId());
        dto.setRutCliente(testDrive.getRutCliente());
        dto.setIdVehiculo(testDrive.getIdVehiculo());
        dto.setRutVendedor(testDrive.getRutVendedor());
        dto.setFechaHoraPrueba(testDrive.getFechaHoraPrueba());
        dto.setEstado(testDrive.getEstado());
        return dto;
    }

    private void validarVendedor(String rutVendedor) {
        log.info("Consultando MS-Vendedores para verificar RUT de empleado: {}", rutVendedor);
        try {
            // Asumimos que MS-Vendedores está en el puerto 8082
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/vendedores/rut/" + rutVendedor)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Vendedor con RUT {} no encontrado en el sistema", rutVendedor);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vendedor asignado no existe en los registros.");
        } catch (Exception ex) {
            log.error("Error de comunicación con MS-Vendedores: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servicio de vendedores no disponible");
        }
    }
}