package com.example.barcodeapp.controller;

import com.example.barcodeapp.model.Operation;
import com.example.barcodeapp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class BarcodeController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/scan")
    public Operation scanBarcode(@RequestParam String userId, @RequestParam String typeOperation,
                                 @RequestParam String barcode, @RequestParam String location) {
        return operationService.saveOperation(userId, typeOperation, barcode, location);
    }

    @GetMapping("/search")
    public Operation searchBarcode(@RequestParam String barcode) {
        return operationService.getOperationByBarcode(barcode);
    }
}
