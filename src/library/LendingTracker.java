package library;

import java.io.*;
import java.util.*;

public class LendingTracker {
    private Queue<Transaction> borrowQueue;
    private Stack<Transaction> returnStack;
    private final String FILE_NAME = "transactions.txt";

    public LendingTracker() {
        borrowQueue = new LinkedList<>();
        returnStack = new Stack<>();
        loadTransactions();
    }

    public void borrowBook(String isbn, String borrowerId, String borrowDate) {
        Transaction t = new Transaction(isbn, borrowerId, borrowDate, "-", "BORROWED");
        borrowQueue.add(t);
        saveTransactions();
        System.out.println("Borrow transaction recorded.");
    }

    public void returnBook(String isbn, String borrowerId, String returnDate) {
        Transaction t = new Transaction(isbn, borrowerId, "-", returnDate, "RETURNED");
        returnStack.push(t);
        saveTransactions();
        System.out.println("Return transaction recorded.");
    }

    public void viewBorrowed() {
        System.out.println("\n--- Borrowed Transactions ---");
        for (Transaction t : borrowQueue) {
            System.out.println(" - " + t);
        }
    }

    public void viewReturned() {
        System.out.println("\n--- Returned Transactions ---");
        for (Transaction t : returnStack) {
            System.out.println(" - " + t);
        }
    }

    private void loadTransactions() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Transaction t = Transaction.deserialize(line);
                if (t != null) {
                    if (t.status.equals("BORROWED")) {
                        borrowQueue.add(t);
                    } else {
                        returnStack.push(t);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : borrowQueue) {
                writer.println(t.serialize());
            }
            for (Transaction t : returnStack) {
                writer.println(t.serialize());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}
