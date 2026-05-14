package com.automaster.ms_testdrive.service;

public class TestDriveService {
    private final WebClient webClient;

    public TestDriveService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public void agendarPrueba(Long idCliente) {
        // Llamada al otro microservicio para validar si el cliente existe
        Boolean existe = webClient.get()
                .uri("/clientes/existe/{id}", idCliente)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // .block() lo hace síncrono
    }
}
