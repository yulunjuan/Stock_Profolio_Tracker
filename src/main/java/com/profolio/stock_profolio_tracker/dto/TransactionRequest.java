package com.profolio.stock_profolio_tracker.dto;

import com.profolio.stock_profolio_tracker.entity.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {

  @NotBlank(message = "Symbol is required")
  @Size(max = 20, message = "Symbol must not exceed 20 characters")
  private String symbol;

  @NotNull(message = "Quantity is required")
  @DecimalMin(value = "0.00000001", message = "Quantity must be greater than 0")
  private BigDecimal quantity;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.00000001", message = "Price must be greater than 0")
  private BigDecimal price;

  @NotNull(message = "Transaction date is required")
  private LocalDate transactionDate;

  @NotNull(message = "Transaction type is required")
  private TransactionType type;
}