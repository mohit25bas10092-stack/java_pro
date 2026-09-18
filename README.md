# Bank Account Manager

A command-line Java application that simulates the core operations of a small bank: opening accounts, depositing and withdrawing money, transferring funds between accounts, and generating a mini statement with interest estimation for savings accounts.

## Overview

Manual ledgers or scattered notes make it easy to lose track of account balances and transaction history. This project provides a simple, offline system to manage multiple bank accounts, enforce basic banking rules (like minimum balance requirements), and keep an auditable record of every transaction.

## Features

- Open a new account (savings or current) with an initial deposit
- View all accounts and their current balances
- Deposit and withdraw money, with minimum balance enforcement for savings accounts
- Transfer money between two accounts in a single operation
- View a mini statement for any account, including full transaction history
- Automatic annual interest estimation for savings accounts (4% per annum)
- Close an account (only allowed once its balance is zero)
- All accounts and transactions are saved to text files and reloaded automatically on the next run

## Technologies Used

- Java (JDK 17 or newer recommended)
- Plain text file storage (no external database needed)
- Core Java only — no external libraries required

## Project Structure

```
bank-account-manager/
├── src/
│   ├── Main.java                  # CLI menu and program entry point
│   ├── Account.java               # Represents one bank account
│   ├── Transaction.java           # Represents one transaction record
│   ├── AccountManager.java        # Open/close/find accounts
│   ├── TransactionProcessor.java  # Deposit/withdraw/transfer logic
│   ├── InterestCalculator.java    # Savings account interest calculation
│   ├── StatementGenerator.java    # Builds and saves mini statements
│   └── FileStorage.java           # Saves/loads accounts & transactions
├── data/                           # Created automatically at runtime
│   ├── accounts.txt
│   ├── transactions.txt
│   └── statement_<accountNumber>.txt
├── statement.md
└── README.md
```

## Setup & Installation

### 1. Install Java

Check if you already have a JDK (version 17+):

```bash
java -version
```

If not installed:

- **Windows/Mac**: Download from [Adoptium](https://adoptium.net/) and install.
- **Linux (Debian/Ubuntu)**:
  ```bash
  sudo apt-get update
  sudo apt-get install default-jdk
  ```

### 2. Clone the repository

```bash
git clone https://github.com/{your-username}/{your-repo-name}.git
cd {your-repo-name}
```

### 3. Compile the project

```bash
javac -d out src/*.java
```

### 4. Run the project

```bash
java -cp out Main
```

You'll see a menu like this:

```
What do you want to do?
1. Open a new account
2. View all accounts
3. Deposit money
4. Withdraw money
5. Transfer money
6. View mini statement
7. Close an account
0. Save and exit
>
```

## How to Test It

1. Run the program and choose `1` to open two accounts — make one `savings` and one `current`.
2. Choose `3` to deposit money into one of them.
3. Choose `4` and try withdrawing an amount that would take a savings account below Rs.500 — it should be denied.
4. Choose `5` to transfer money from one account to the other.
5. Choose `6` to view the mini statement for each account — check the transaction history and, for the savings account, the estimated interest.
6. Choose `7` and try closing an account with a remaining balance — it should be denied until the balance is zero.
7. Choose `0` to save and exit, then run the program again — accounts and transaction history should still be there.

## Notes

- Account numbers are generated automatically starting from `AC1001`.
- Savings accounts require a minimum opening deposit of Rs.500 and must always maintain that minimum balance.
- Current accounts have no minimum balance requirement.
- Interest is calculated at a fixed rate of 4% per annum for savings accounts only, purely as an estimate — no interest is actually credited to the balance.
