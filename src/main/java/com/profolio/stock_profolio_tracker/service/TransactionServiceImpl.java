package com.profolio.stock_profolio_tracker.service;

import com.profolio.stock_profolio_tracker.dto.TransactionRequest;
import com.profolio.stock_profolio_tracker.dto.TransactionResponse;
import com.profolio.stock_profolio_tracker.entity.Transaction;
import com.profolio.stock_profolio_tracker.exception.TransactionNotFoundException;
import com.profolio.stock_profolio_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  @Override
  @Transactional
  public TransactionResponse createTransaction(TransactionRequest request) {
    Transaction transaction = toEntity(request);
    return toResponse(transactionRepository.save(transaction));
  }

  @Override
  @Transactional(readOnly = true)
  public TransactionResponse getTransactionById(Long id) {
    return toResponse(findById(id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> getAllTransactions() {
    return transactionRepository.findAll().stream()
        .map(this::toResponse)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> getTransactionsBySymbol(String symbol) {
    return transactionRepository.findBySymbolOrderByTransactionDateAsc(symbol.toUpperCase()).stream()
        .map(this::toResponse)
        .toList();
  }

  @Override
  @Transactional
  public TransactionResponse updateTransaction(Long id, TransactionRequest request) {
    Transaction transaction = findById(id);
    transaction.setSymbol(request.getSymbol().toUpperCase());
    transaction.setQuantity(request.getQuantity());
    transaction.setPrice(request.getPrice());
    transaction.setTransactionDate(request.getTransactionDate());
    transaction.setType(request.getType());
    return toResponse(transactionRepository.save(transaction));
  }

  @Override
  @Transactional
  public void deleteTransaction(Long id) {
    findById(id);
    transactionRepository.deleteById(id);
  }

  private Transaction findById(Long id) {
    return transactionRepository.findById(id)
        .orElseThrow(() -> new TransactionNotFoundException(id));
  }

  private Transaction toEntity(TransactionRequest request) {
    Transaction transaction = new Transaction();
    transaction.setSymbol(request.getSymbol().toUpperCase());
    transaction.setQuantity(request.getQuantity());
    transaction.setPrice(request.getPrice());
    transaction.setTransactionDate(request.getTransactionDate());
    transaction.setType(request.getType());
    return transaction;
  }

  private TransactionResponse toResponse(Transaction transaction) {
    TransactionResponse response = new TransactionResponse();
    response.setId(transaction.getId());
    response.setSymbol(transaction.getSymbol());
    response.setQuantity(transaction.getQuantity());
    response.setPrice(transaction.getPrice());
    response.setTotalValue(transaction.getQuantity().multiply(transaction.getPrice()));
    response.setTransactionDate(transaction.getTransactionDate());
    response.setType(transaction.getType());
    response.setCreatedAt(transaction.getCreatedAt());
    response.setUpdatedAt(transaction.getUpdatedAt());
    return response;
  }
}