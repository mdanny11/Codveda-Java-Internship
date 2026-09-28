# Level 2 — Simple Banking

Console banking program. Accounts are stored in a `HashMap` and support open, deposit, withdraw, and balance check.

## Setup

Requires JDK 17 or newer. From this folder:

```powershell
javac src\BankAccount.java src\Bank.java src\Main.java -d out
java -cp out Main
```

Balances stay in memory and are gone when the program exits.

## Features

- `BankAccount` deposits, withdraws, and reports a balance.
- `Bank` keeps accounts by account number.
- A duplicate account number is rejected.
- An empty account number, an empty holder name, or a negative opening balance is rejected.
- A deposit or withdrawal of zero or less prints `Amount must be greater than zero.`
- A withdrawal above the balance prints `Insufficient funds.`
- A missing account prints `No account with number ...`.
- A word instead of an amount prints `Enter a valid number.` and asks again.

## Sample run

```text
Simple Banking
Enter 5 to exit.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 1
Account number: A100
Account holder: Ada Lovelace
Opening balance: 1000
Account opened.
A100 | Ada Lovelace | Balance: 1000.0

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 1
Account number: A100
Account A100 already exists.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 1
Account number: A200
Account holder: 
Account holder name cannot be empty.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 1
Account number: A200
Account holder: Grace Hopper
Opening balance: -50
Opening balance cannot be negative.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 2
Account number: A100
Amount: 250
Deposited 250.0. Balance: 1250.0

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 2
Account number: A100
Amount: 0
Amount must be greater than zero.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 2
Account number: ZZZ
No account with number ZZZ.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 3
Account number: A100
Amount: 2000
Insufficient funds.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 3
Account number: A100
Amount: -10
Amount must be greater than zero.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 3
Account number: A100
Amount: ten
Enter a valid number.
Amount: 400
Withdrew 400.0. Balance: 850.0

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 4
Account number: A100
A100 | Ada Lovelace | Balance: 850.0

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 4
Account number: B200
No account with number B200.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: 8
Choose a number from 1 to 5.

1. Open account
2. Deposit
3. Withdraw
4. Check balance
5. Exit
Choice: abc
Enter a whole number.
Choice: 5
Goodbye.
```
