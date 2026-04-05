package com.profolio.stock_profolio_tracker.service;

import com.profolio.stock_profolio_tracker.dto.TransactionRequest;
import com.profolio.stock_profolio_tracker.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {

  TransactionResponse createTransaction(TransactionRequest request);

  TransactionResponse getTransactionById(Long id);

  List<TransactionResponse> getAllTransactions();

  List<TransactionResponse> getTransactionsBySymbol(String symbol);

  TransactionResponse updateTransaction(Long id, TransactionRequest request);

  void deleteTransaction(Long id);
}