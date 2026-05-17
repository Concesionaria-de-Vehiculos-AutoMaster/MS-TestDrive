package com.automaster.mstestdrive.repository;

import com.automaster.mstestdrive.model.TestDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Long> {
    // Hereda todos los métodos CRUD necesarios (save, findById, findAll, etc.)
}