package com.profolio.stock_profolio_tracker.repository;

import com.profolio.stock_profolio_tracker.entity.Transaction;
import com.profolio.stock_profolio_tracker.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Transaction entity
 * Spring Data JPA automatically implements all methods
 *
 * Naming convention for query methods:
 * - findBy[Field]
 * - findBy[Field1]And[Field2]
 * - findBy[Field]OrderBy[AnotherField]Asc/Desc
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  // ===== Basic Queries =====

  /**
   * Find all transactions for a specific stock symbol
   *
   * SQL: SELECT * FROM transactions WHERE symbol = ?
   */
  List<Transaction> findBySymbol(String symbol);

  /**
   * Find all transactions of a specific type (BUY or SELL)
   *
   * SQL: SELECT * FROM transactions WHERE type = ?
   */
  List<Transaction> findByType(TransactionType type);

  /**
   * Find transactions for a symbol with specific type
   *
   * SQL: SELECT * FROM transactions WHERE symbol = ? AND type = ?
   */
  List<Transaction> findBySymbolAndType(String symbol, TransactionType type);

  // ===== FIFO Calculation Queries =====

  /**
   * Find transactions for a symbol, ordered by date (oldest first)
   * Critical for FIFO calculation
   *
   * SQL: SELECT * FROM transactions WHERE symbol = ? ORDER BY transaction_date ASC
   */
  List<Transaction> findBySymbolOrderByTransactionDateAsc(String symbol);

  /**
   * Find BUY transactions for a symbol, ordered by date
   * Used for FIFO cost basis calculation
   *
   * SQL: SELECT * FROM transactions WHERE symbol = ? AND type = 'BUY' ORDER BY transaction_date ASC
   */
  List<Transaction> findBySymbolAndTypeOrderByTransactionDateAsc(
      String symbol,
      TransactionType type
  );

  // ===== Portfolio Summary Queries =====

  /**
   * Find all unique stock symbols in portfolio
   *
   * SQL: SELECT DISTINCT symbol FROM transactions
   */
  @Query("SELECT DISTINCT t.symbol FROM Transaction t")
  List<String> findAllUniqueSymbols();

  /**
   * Find transactions within a date range
   * Useful for performance analytics
   *
   * SQL: SELECT * FROM transactions WHERE transaction_date BETWEEN ? AND ?
   */
  List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

  /**
   * Count total transactions for a symbol
   *
   * SQL: SELECT COUNT(*) FROM transactions WHERE symbol = ?
   */
  Long countBySymbol(String symbol);

  // ===== Advanced Queries (Custom JPQL) =====

  /**
   * Find all BUY transactions for symbols currently in portfolio
   * Custom JPQL query for complex logic
   */
  @Query("SELECT t FROM Transaction t WHERE t.type = 'BUY' ORDER BY t.transactionDate ASC")
  List<Transaction> findAllPurchases();

  /**
   * Calculate total quantity for a symbol
   * Note: This returns sum, not Transaction objects
   */
  @Query("SELECT SUM(CASE WHEN t.type = 'BUY' THEN t.quantity ELSE -t.quantity END) " +
      "FROM Transaction t WHERE t.symbol = :symbol")
  Double calculateNetQuantity(String symbol);
}
