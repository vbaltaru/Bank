# Banking Application

## Description

This is a desktop banking application built with Java and JavaFX. It simulates a core banking system allowing users to register, log in, view their account details, and transfer money to other users. The system supports multi-currency accounts and handles currency conversion automatically during transfers.

## Key Features

### Account Management

- **Registration:** Users can sign up by providing personal details including Name, CNP, Address, and Phone number.
- **Validation:** The system ensures data integrity with checks for:
  - Age (must be 18+).
  - CNP format (13 digits).
  - Phone number format (10 digits).
  - PIN security (6 digits).
- **Currency Logic:** Account currency is assigned based on the declared country (Romania -> RON, USA -> USD, Others -> EUR).

### Transactions

- **Transfers:** Secure money transfers between users using email addresses.
- **Currency Conversion:** Built-in exchange rate logic handles transfers between different currencies (RON, EUR, USD).
- **Balance Checks:** Prevents overdrafts by validating sufficient funds before transactions.

## Technical Stack

- **Language:** Java
- **GUI Framework:** JavaFX
- **Database:** MySQL
- **Build Tool:** Maven

## Configuration

To run this application, ensure you have a MySQL database running on localhost (port 3306) named `cont_bancar`. The application connects using the root user.
