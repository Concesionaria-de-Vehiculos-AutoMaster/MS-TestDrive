package com.automaster.ms_testdrive.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.automaster.ms_testdrive.dto.ClienteResponseDTO;

@FeignClient(name = "ms-clientes", url = "http://localhost:8080/api/clientes")

public interface ClienteClient {

    @GetMapping("/{id}")
    ClienteResponseDTO obtenerPorId(@PathVariable("id") Long id);
}
