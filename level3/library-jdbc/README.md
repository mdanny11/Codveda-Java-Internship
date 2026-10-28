# Level 3 — Library Management with JDBC

Console library that stores books, users, and borrow history through JDBC.

The database is an embedded H2 file in `data/`. No separate database server is required. Borrow and return each run as one database transaction: the book status and the history row are saved together, or neither change is kept. Data remains after the program exits. The `data/` folder is not part of the source.

## Setup

Requires JDK 17 or newer. The H2 driver is in `lib`. From this folder:

```powershell
javac -cp "lib\h2-2.3.232.jar" src\LibraryStore.java src\Main.java -d out
java -cp "out;lib\h2-2.3.232.jar" Main
```

## Features

- Books have a title, an author, and an available or borrowed status.
- Users have a name.
- Every borrow and return is stored in a transactions table.
- Add, list, update, and delete work for books and users.
- A book with borrow history cannot be deleted. A user with borrow history cannot be deleted.
- Borrowing an unavailable book, returning a book that is not borrowed, and returning someone else's loan are rejected.
- An empty title, author, or name is rejected.

## Sample run

```text
Library Management
Enter 11 to exit.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 1
Title: The Hobbit
Author: J.R.R. Tolkien
Book added. Id: 1

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 1
Title: 
Title cannot be empty.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 5
Member name: Ada Lovelace
User added. Id: 1

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 5
Member name: 
Name cannot be empty.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 5
Member name: Grace Hopper
User added. Id: 2

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 2
Books:
1 | The Hobbit | J.R.R. Tolkien | available

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 6
Users:
1 | Ada Lovelace
2 | Grace Hopper

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 9
Book id: 99
No book with id 99.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 9
Book id: 1
User id: 99
No user with id 99.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 9
Book id: 1
User id: 1
Book borrowed.
1 | The Hobbit | J.R.R. Tolkien | borrowed

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 9
Book id: 1
User id: 2
That book is already borrowed.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 10
Book id: 1
User id: 2
That book is borrowed by another user.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 10
Book id: 1
User id: 1
Book returned.
1 | The Hobbit | J.R.R. Tolkien | available

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 10
Book id: 1
User id: 1
That book is not currently borrowed.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 3
Book id: 50
No book with id 50.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 3
Book id: 1
Title: The Hobbit
Author: Tolkien
Book updated.
1 | The Hobbit | Tolkien | available

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 4
Book id: 1
Cannot delete a book that has borrow history.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 1
Title: Clean Code
Author: Robert Martin
Book added. Id: 2

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 4
Book id: 2
Book deleted.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 7
User id: 1
Member name: Ada Byron
User updated.
1 | Ada Byron

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 8
User id: 1
Cannot delete a user who has borrow history.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 8
User id: 2
User deleted.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: 12
Choose a number from 1 to 11.

1. Add book
2. List books
3. Update book
4. Delete book
5. Add user
6. List users
7. Update user
8. Delete user
9. Borrow book
10. Return book
11. Exit
Choice: abc
Enter a whole number.
Choice: 11
Goodbye.
```
