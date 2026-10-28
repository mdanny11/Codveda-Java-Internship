# Level 3 — Binary Search Tree

Console program that inserts, deletes, searches, and prints a binary search tree.

## Setup

Requires JDK 17 or newer. From this folder:

```powershell
javac src\TreeNode.java src\BinarySearchTree.java src\Main.java -d out
java -cp out Main
```

## Features

- `TreeNode` stores a value and links to the left and right children.
- Smaller values go left. Larger values go right. A duplicate is rejected.
- Delete removes a leaf, a node with one child, or a node with two children.
- A node with two children is replaced by the smallest value in its right subtree.
- In-order, pre-order, and post-order traversals are printed from the menu.
- A missing value and a word instead of a number are rejected.

## Sample run

```text
Binary Search Tree
Enter 7 to exit.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 50
Inserted 50.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 30
Inserted 30.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 70
Inserted 70.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 20
Inserted 20.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 40
Inserted 40.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 60
Inserted 60.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 80
Inserted 80.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 1
Value: 50
50 is already in the tree.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 4
In-order: 20 30 40 50 60 70 80

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 5
Pre-order: 50 30 20 40 70 60 80

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 6
Post-order: 20 40 30 60 80 70 50

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 3
Value: 40
40 is in the tree.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 3
Value: 15
15 is not in the tree.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 2
Value: 20
Deleted 20.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 4
In-order: 30 40 50 60 70 80

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 2
Value: 30
Deleted 30.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 4
In-order: 40 50 60 70 80

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 2
Value: 50
Deleted 50.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 4
In-order: 40 60 70 80

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 2
Value: 99
99 is not in the tree.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: 9
Choose a number from 1 to 7.

1. Insert
2. Delete
3. Search
4. In-order traversal
5. Pre-order traversal
6. Post-order traversal
7. Exit
Choice: abc
Enter a whole number.
Choice: 7
Goodbye.
```
