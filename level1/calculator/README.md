# Level 1 — Basic Calculator

Console calculator for addition, subtraction, multiplication, and division.

## Setup

Requires JDK 17 or newer. From this folder:

```powershell
javac src\Calculator.java src\Main.java -d out
java -cp out Main
```

## Features

- `Calculator` has `add`, `subtract`, `multiply`, and `divide`.
- The menu asks for an operation, then two numbers.
- Division by zero prints `Cannot divide by zero.` and returns to the menu.
- A menu number outside 1–5 prints `Choose a number from 1 to 5.`
- A word instead of a menu number prints `Enter a whole number from 1 to 5.`
- A word instead of a number prints `Enter a valid number.` and asks again.

## Sample run

```text
Basic Calculator
Enter 5 to exit.

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 1
First number: 10
Second number: 5
Result: 15.0

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 2
First number: 20
Second number: 8
Result: 12.0

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 3
First number: 6
Second number: 7
Result: 42.0

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 4
First number: 10
Second number: 0
Cannot divide by zero.

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 4
First number: ten
Enter a valid number.
First number: 8
Second number: 2
Result: 4.0

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: 9
Choose a number from 1 to 5.

1. Add
2. Subtract
3. Multiply
4. Divide
5. Exit
Operation: abc
Enter a whole number from 1 to 5.
Operation: 5
Goodbye.
```

Run the program and capture this menu for the LinkedIn screenshot. Save the image in this folder as `screenshot.png` if you want it stored with the project.
