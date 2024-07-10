package com.example.barcodeapp.service;

import com.example.barcodeapp.model.Operation;
import com.example.barcodeapp.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public Operation saveOperation(String userId, String typeOperation, String barcode, String location) {
        Operation operation = new Operation();
        operation.setUserId(userId);
        operation.setOperationType(typeOperation);
        operation.setBarcode(barcode);
        operation.setLocation(location);
        operation.setDate(LocalDateTime.now());
        return operationRepository.save(operation);
    }

    public Operation getOperationByBarcode(String barcode) {
        return operationRepository.findByBarcode(barcode);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }
}
