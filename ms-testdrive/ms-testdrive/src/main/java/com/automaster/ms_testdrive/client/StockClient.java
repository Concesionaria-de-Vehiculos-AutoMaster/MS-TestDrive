package com.automaster.ms_testdrive.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.automaster.ms_testdrive.dto.VehiculoResponseDTO;

@FeignClient(name = "ms-stock", url = "http://localhost:8081/api/stock")
public interface StockClient {

    @GetMapping("/{id}")
    VehiculoResponseDTO obtenerVehiculoPorId(@PathVariable("id") Long id);

}
