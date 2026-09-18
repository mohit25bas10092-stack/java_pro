# Bank Account Manager - Project Statement

## Objective
The project is a command-line Java application that simulates basic banking operations.

## Main Operations
- Open savings/current accounts
- View all accounts
- Deposit money
- Withdraw money
- Transfer money
- View mini statements
- Estimate 4% annual interest for savings accounts
- Close accounts only when balance is zero

## Rules
- Savings accounts need a minimum opening deposit of Rs.500.
- Savings accounts must maintain a minimum balance of Rs.500.
- Current accounts have no minimum balance requirement.
- Account numbers start from AC1001.
- Interest is an estimate only and is not added to the account balance.

## Storage
The program uses plain text files:
- data/accounts.txt
- data/transactions.txt
- data/statement_<accountNumber>.txt

## Architecture
The application is organized around:
- Main
- Account
- Transaction
- AccountManager
- TransactionProcessor
- InterestCalculator
- StatementGenerator
- FileStorage
