package com.profolio.stock_profolio_tracker.controller;

import com.profolio.stock_profolio_tracker.dto.TransactionRequest;
import com.profolio.stock_profolio_tracker.dto.TransactionResponse;
import com.profolio.stock_profolio_tracker.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.createTransaction(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(transactionService.getTransactionById(id));
  }

  @GetMapping
  public ResponseEntity<List<TransactionResponse>> getAll() {
    return ResponseEntity.ok(transactionService.getAllTransactions());
  }

  @GetMapping("/symbol/{symbol}")
  public ResponseEntity<List<TransactionResponse>> getBySymbol(@PathVariable String symbol) {
    return ResponseEntity.ok(transactionService.getTransactionsBySymbol(symbol));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TransactionResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody TransactionRequest request) {
    return ResponseEntity.ok(transactionService.updateTransaction(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    transactionService.deleteTransaction(id);
    return ResponseEntity.noContent().build();
  }
}