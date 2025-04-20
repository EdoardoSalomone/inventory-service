package it.edoardo.inventoryservice.repository;

import it.edoardo.inventoryservice.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
}
