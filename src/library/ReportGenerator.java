package library;

import java.io.File;
import java.util.*;

public class ReportGenerator {
    private BookInventory inventory;
    private BorrowerRegistry registry;

    public ReportGenerator(BookInventory inventory, BorrowerRegistry registry) {
        this.inventory = inventory;
        this.registry = registry;
    }

    public void mostBorrowedBooks() {
        System.out.println("\n--- Most Borrowed Books ---");
        Map<String, Integer> borrowCount = new HashMap<>();

        try (Scanner sc = new Scanner(new File("transactions.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Transaction t = Transaction.deserialize(line);
                if (t != null && t.status.equals("BORROWED")) {
                    borrowCount.put(t.isbn, borrowCount.getOrDefault(t.isbn, 0) + 1);
                }
            }

            borrowCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .forEach(e -> System.out.println("ISBN: " + e.getKey() + " - Borrowed: " + e.getValue() + " times"));

        } catch (Exception e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    public void topFinedBorrowers() {
        System.out.println("\n--- Borrowers with Highest Outstanding Fines ---");
        List<Borrower> all = registry.getAllBorrowers();
        all.sort((a, b) -> Double.compare(b.fine, a.fine));

        for (int i = 0; i < Math.min(5, all.size()); i++) {
            Borrower b = all.get(i);
            if (b.fine > 0) {
                System.out.println(b.name + " (ID: " + b.id + ") - GH₵" + b.fine);
            }
        }
    }

    public void inventoryDistribution() {
        System.out.println("\n--- Inventory Distribution by Category ---");
        Map<String, Integer> categoryCount = new HashMap<>();

        for (Book b : inventory.getAllBooksFlat()) {
            categoryCount.put(b.category, categoryCount.getOrDefault(b.category, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " book(s)");
        }
    }
}
