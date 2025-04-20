package it.edoardo.inventoryservice.model;

import it.edoardo.inventoryservice.enumeration.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "product_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer productId;
  private String productName;
  private int availableQuantity;

  @Enumerated(EnumType.STRING)
  private UnitType unitType;

  private double unitPrice;
  private String category;

  private LocalDate createdAt;
}
