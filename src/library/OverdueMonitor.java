package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OverdueMonitor {
    private PriorityQueue<Transaction> overdueQueue;
    private BorrowerRegistry registry;
    private static final int MAX_DAYS = 14;

    public OverdueMonitor(BorrowerRegistry registry) {
        this.registry = registry;
        overdueQueue = new PriorityQueue<>(Comparator.comparing(this::parseBorrowDate));
        loadOverdueBorrowedBooks();
    }

    private void loadOverdueBorrowedBooks() {
        try (Scanner sc = new Scanner(new java.io.File("transactions.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Transaction t = Transaction.deserialize(line);
                if (t != null && t.status.equals("BORROWED")) {
                    overdueQueue.offer(t);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    private LocalDate parseBorrowDate(Transaction t) {
        try {
            return LocalDate.parse(t.borrowDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return LocalDate.MIN;
        }
    }

    public void checkAndUpdateOverdues() {
        LocalDate today = LocalDate.now();
        boolean found = false;

        while (!overdueQueue.isEmpty()) {
            Transaction t = overdueQueue.peek();
            LocalDate borrowDate = parseBorrowDate(t);
            if (borrowDate.equals(LocalDate.MIN)) {
                overdueQueue.poll();
                continue;
            }

            long daysBorrowed = java.time.temporal.ChronoUnit.DAYS.between(borrowDate, today);
            if (daysBorrowed <= MAX_DAYS) break;

            // Book is overdue
            System.out.println("Overdue: " + t);
            Borrower b = registry.searchBorrower(t.borrowerId);
            if (b != null) {
                double newFine = b.fine + 2.0; // GH₵2 per overdue book
                b.fine = newFine;
                registry.updateBorrower(b);
                System.out.println("Fine updated to GH₵" + newFine);
                found = true;
            }

            overdueQueue.poll(); // remove from queue after processing
        }

        if (!found) {
            System.out.println("No overdue books at this time.");
        }
    }
}
