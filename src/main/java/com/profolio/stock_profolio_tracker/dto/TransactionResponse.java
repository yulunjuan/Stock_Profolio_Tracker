package com.profolio.stock_profolio_tracker.dto;

import com.profolio.stock_profolio_tracker.entity.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {

  private Long id;
  private String symbol;
  private BigDecimal quantity;
  private BigDecimal price;
  private BigDecimal totalValue;
  private LocalDate transactionDate;
  private TransactionType type;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}