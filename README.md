
# 📚 Ebenezer Library Management System

A console-based, offline-first Book Lending & Cataloguing System developed in Java for the **Ebenezer Community Library in Ashaiman**. This system helps librarians manage books, borrowers, lending, returns, overdue tracking, and reports — all without the need for a GUI or database.

---

## 🧩 Project Overview

### 🔧 Built With:
- **Java (JDK 24)**
- **Core Data Structures:** `HashMap`, `ArrayList`, `Queue`, `Stack`, `PriorityQueue`
- **No external libraries or frameworks**
- **Plain text files for storage** (`.txt` files)

---

## 📂 Features

### ✅ Book Inventory Management
- Add, remove, and list books
- Organize books by category using `HashMap`
- Persistent storage in `books.txt`

### ✅ Search & Sorting
- **Search** by title, author, or ISBN using linear search
- **Sort** books:
  - By Title – `Selection Sort`
  - By Year – `Merge Sort`

### ✅ Borrower Registry
- Add/remove borrowers
- Recursive search by borrower ID
- Fine tracking and storage in `borrowers.txt`

### ✅ Lending Tracker
- Track borrowing with `Queue`
- Track returns with `Stack`
- Transactions stored in `transactions.txt`

### ✅ Overdue Monitoring
- Uses `PriorityQueue` (min-heap) to find overdue books
- Flags books overdue by more than 14 days
- Automatically updates borrower fines

### ✅ Reports
- Most borrowed books
- Borrowers with highest fines
- Book category distribution

---

## 📁 Project Structure

```

BookInventorySystem/
├── src/
│   └── library/
│       ├── Book.java
│       ├── BookInventory.java
│       ├── Borrower.java
│       ├── BorrowerRegistry.java
│       ├── Transaction.java
│       ├── LendingTracker.java
│       ├── OverdueMonitor.java
│       ├── SearchSort.java
│       ├── ReportGenerator.java
│       ├── Main.java
│       └── SeedData.java
├── books.txt
├── borrowers.txt
├── transactions.txt
├── README.md


````

---

## 🚀 Getting Started

### ✅ Requirements
- Java JDK 8 or later
- A terminal (CMD, Git Bash, etc.)

### ✅ Compile All Classes

From the root directory (`EbenezerLibrarySystem`):

```bash
javac library/*.java
````

### ✅ Populate Dummy Data (Optional)

To test the system with sample data:

```bash
java library.SeedData
```

### ✅ Run the System

```bash
java library.Main
```

---

## 🧪 Sample Menu Options

```text
1. Add Book               10. View All Borrowers
2. Remove Book            11. Search Borrower by ID
3. List All Books         12. Borrow Book
4. List Books by Category 13. Return Book
5. Search Book            14. View Borrowed Transactions
6. Sort Books by Title    15. View Returned Transactions
7. Sort Books by Year     16. Check Overdue Books
8. Add Borrower           17. Report: Most Borrowed Books
9. Remove Borrower        18. Report: Top Fined Borrowers
                          19. Report: Inventory by Category
0. Exit
```

---

## 📊 Algorithm Notes

| Feature            | Data Structure / Algorithm | Time Complexity |
| ------------------ | -------------------------- | --------------- |
| Search Book        | Linear Search              | O(n)            |
| Sort by Title      | Selection Sort             | O(n²)           |
| Sort by Year       | Merge Sort                 | O(n log n)      |
| Borrower Search    | Recursion                  | O(n)            |
| Overdue Monitoring | Min-Heap (PriorityQueue)   | O(n log n)      |
| Lending/Returning  | Queue / Stack              | O(1) insert     |

---

## 📌 Notes

* Designed for **offline use** in low-resource environments
* No GUI, database, or internet required
* Ideal for educational use or small libraries in developing communities

