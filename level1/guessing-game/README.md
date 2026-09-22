# Level 1 — Number Guessing Game

Console game that picks a number from 1 to 100 and gives you 7 attempts to guess it.

## Setup

Requires JDK 17 or newer. From this folder:

```powershell
javac src\GuessingGame.java src\Main.java -d out
java -cp out Main
```

## Features

- `GuessingGame` uses `Random` to choose a secret from 1 to 100.
- Each guess is too high, too low, or correct.
- You get 7 attempts. Running out reveals the number.
- A word instead of a number prints `Enter a whole number.` and is not counted.
- A number outside 1–100 is not counted.
- `y` or `yes` starts another round. Any other answer exits.

## Sample run

```text
Number Guessing Game
I am thinking of a number from 1 to 100.
You have 7 attempts.
Your guess: abc
Enter a whole number.
Your guess: 0
Enter a number from 1 to 100. That guess was not counted.
Your guess: 50
Too high. Attempts left: 6
Your guess: 25
Too high. Attempts left: 5
Your guess: 12
Too high. Attempts left: 4
Your guess: 6
Too high. Attempts left: 3
Your guess: 3
Correct. The number was 3.
Play again? (y/n): y
You have 7 attempts.
Your guess: 1
Too low. Attempts left: 6
Your guess: 1
Too low. Attempts left: 5
Your guess: 1
Too low. Attempts left: 4
Your guess: 1
Too low. Attempts left: 3
Your guess: 1
Too low. Attempts left: 2
Your guess: 1
Too low. Attempts left: 1
Your guess: 1
Too low. Attempts left: 0
Out of attempts. The number was 77.
Play again? (y/n): n
Goodbye.
```
