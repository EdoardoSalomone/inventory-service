package it.edoardo.inventoryservice.controller;

import it.edoardo.inventoryservice.dto.GenericResponse;
import it.edoardo.inventoryservice.dto.OrderRequestDTO;
import it.edoardo.inventoryservice.model.ProductStock;
import it.edoardo.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/decrease")
    public ResponseEntity<GenericResponse> decreaseStock(@RequestBody OrderRequestDTO request) {
        GenericResponse response = inventoryService.decreaseInventory(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/product/names")
    public ResponseEntity<Map<Integer,String>> getProductNames(@RequestBody List<Integer> request) {
        return ResponseEntity.ok(inventoryService.buildProductMap(inventoryService.findAllProductsByIds(request)));
    }
}