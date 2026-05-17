package com.automaster.ms_testdrive.repository;

import com.automaster.ms_testdrive.model.TestDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long> {

    List<TestDrive> findByIdCliente(Long idCliente);

    List<TestDrive> findByIdVehiculo(Long idVehiculo);
}
