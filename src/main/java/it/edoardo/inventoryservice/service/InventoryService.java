package it.edoardo.inventoryservice.service;

import it.edoardo.inventoryservice.dto.GenericResponse;
import it.edoardo.inventoryservice.dto.OrderItemDTO;
import it.edoardo.inventoryservice.dto.OrderRequestDTO;
import it.edoardo.inventoryservice.model.ProductStock;
import it.edoardo.inventoryservice.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductStockRepository repository;

    public GenericResponse decreaseInventory(OrderRequestDTO request) {
        for (OrderItemDTO item : request.getItems()) {
            Optional<ProductStock> stockOpt = repository.findById(item.getProductId());

            if (stockOpt.isEmpty()) {
                return new GenericResponse("FAILURE", "Prodotto non trovato: " + item.getProductName());
            }

            ProductStock stock = stockOpt.get();
            if (stock.getAvailableQuantity() < item.getQuantity()) {
                return new GenericResponse("FAILURE", "Scorte insufficienti per: " + item.getProductName());
            }

            stock.setAvailableQuantity(stock.getAvailableQuantity() - item.getQuantity());
            repository.save(stock);
        }

        return new GenericResponse("SUCCESS", "Inventario aggiornato");
    }

    public List<ProductStock> findAllProductsByIds(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    public Map<Integer,String> buildProductMap (List<ProductStock> stocks) {
        Map<Integer,String> map = stocks.stream().collect(Collectors.toMap(ProductStock::getProductId, ProductStock::getProductName));
        return map;
    }
}
