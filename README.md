# Stock Portfolio Tracker

Investment portfolio tracker with **real-time P&L calculations** using FIFO cost basis algorithm.

## Tech Stack

Java 17 • Spring Boot 3.5.7 • PostgreSQL 15 • Spring Data JPA • Alpha Vantage API

## Features

- Transaction management (buy/sell records)
- FIFO cost basis calculation for tax reporting
- **Real-time portfolio valuation** with live market prices
- **Automated P&L tracking** (realized and unrealized gains)
- Position summary with performance analytics

## Setup

**Prerequisites:** Java 17+, PostgreSQL 15+, Maven, Alpha Vantage API key

1. Clone repository
```bash
git clone https://github.com/yulunjuan/stock-portfolio-tracker.git
```

2. Create database
```sql
CREATE DATABASE portfolio_tracker;
```

3. Configure `application.properties`
```properties
spring.datasource.password=your_password
market.api.key=your_alphavantage_key
```

4. Run
```bash
./mvnw spring-boot:run
```

## API Endpoints
```
POST   /api/transactions          - Record transaction
GET    /api/portfolio/summary     - Get portfolio with real-time P&L
GET    /api/portfolio/positions   - Get all positions with current prices
```

## Author

Yu-Lun Juan - Northeastern University