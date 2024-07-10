package com.example.barcodeapp.repository;

import com.example.barcodeapp.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Operation findByBarcode(String barcode);
}
