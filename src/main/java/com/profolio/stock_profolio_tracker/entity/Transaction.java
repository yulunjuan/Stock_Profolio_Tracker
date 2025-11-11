package com.profolio.stock_profolio_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

  @Column(nullable = false, length = 20)
  private String symbol;

  @Column(nullable = false, precision = 18, scale = 8)
  private BigDecimal quantity;

  @Column(nullable = false, precision = 18, scale = 8)
  private BigDecimal price;

  @Column(name = "transaction_date", nullable = false)
  private LocalDate transactionDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransactionType type;
}
