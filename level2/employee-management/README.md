# Level 2 — Employee Management

Console program that stores employees in an `ArrayList` and supports add, view, search, update, and delete.

## Setup

Requires JDK 17 or newer. From this folder:

```powershell
javac src\Employee.java src\EmployeeDirectory.java src\Main.java -d out
java -cp out Main
```

Records stay in memory and are gone when the program exits.

## Features

- `Employee` stores id, name, department, and salary.
- `EmployeeDirectory` adds, lists, finds, updates, and deletes records.
- A duplicate id is rejected.
- An empty name, an empty department, a non-positive id, or a negative salary is rejected.
- A missing id on search, update, or delete prints `No employee with id ...`.
- A word instead of a menu number prints `Enter a whole number.`

## Sample run

```text
Employee Management
Enter 6 to exit.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 1
Employee id: 1
Name: Ada Lovelace
Department: Engineering
Salary amount: 5000
Employee added.
ID 1 | Ada Lovelace | Engineering | Salary: 5000.0

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 1
Employee id: 1
Employee id 1 already exists.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 1
Employee id: 3
Name: 
Name cannot be empty.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 1
Employee id: 2
Name: Grace Hopper
Department: Engineering
Salary amount: -20
Salary cannot be negative.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 2
Employees:
ID 1 | Ada Lovelace | Engineering | Salary: 5000.0

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 3
Employee id: 99
No employee with id 99.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 3
Employee id: 1
ID 1 | Ada Lovelace | Engineering | Salary: 5000.0

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 4
Employee id: 50
No employee with id 50.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 4
Employee id: 1
Name: Ada Lovelace
Department: Research
Salary amount: -5
Salary cannot be negative.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 4
Employee id: 1
Name: Ada Lovelace
Department: Research
Salary amount: 6200
Employee updated.
ID 1 | Ada Lovelace | Research | Salary: 6200.0

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 2
Employees:
ID 1 | Ada Lovelace | Research | Salary: 6200.0

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 5
Employee id: 1
Employee deleted.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 5
Employee id: 1
No employee with id 1.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 2
No employees yet.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: 9
Choose a number from 1 to 6.

1. Add employee
2. View all employees
3. Search by id
4. Update employee
5. Delete employee
6. Exit
Choice: abc
Enter a whole number.
Choice: 6
Goodbye.
```
