package library;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SeedData {
    public static void main(String[] args) {
        try {
            seedBooks();
            seedBorrowers();
            seedTransactions();
            System.out.println("Dummy data seeded successfully!");
        } catch (Exception e) {
            System.out.println("Operation Failed!");
            throw new RuntimeException(e);
        }

    }

    private static void seedBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            writer.println(new Book("Java Programming", "James Gosling", "978-0001", "Computer Science", "2018", "Oracle Press", "A1").serialize());
            writer.println(new Book("Python Basics", "Guido van Rossum", "978-0002", "Computer Science", "2020", "O'Reilly", "A2").serialize());
            writer.println(new Book("African Literature", "Chinua Achebe", "978-0003", "Literature", "1965", "Heinemann", "B1").serialize());
            writer.println(new Book("Modern Physics", "Stephen Hawking", "978-0004", "Science", "1999", "Bantam", "C1").serialize());
            writer.println(new Book("History of Ghana", "Kwame Nkrumah", "978-0005", "History", "1960", "Pan-African Press", "D2").serialize());
        } catch (Exception e) {
            System.out.println("Failed to seed books: " + e.getMessage());
        }
    }

    private static void seedBorrowers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("borrowers.txt"))) {
            writer.println(new Borrower("Alice Mensah", "B001", "0551234567", 0.0, new ArrayList<>()).serialize());
            writer.println(new Borrower("Kwame Boateng", "B002", "0247654321", 4.0, new ArrayList<>()).serialize());
            writer.println(new Borrower("Akosua Nyame", "B003", "0501122334", 0.0, new ArrayList<>()).serialize());
            writer.println(new Borrower("John Doe", "B004", "0579988776", 2.0, new ArrayList<>()).serialize());
        } catch (Exception e) {
            System.out.println("Failed to seed borrowers: " + e.getMessage());
        }
    }

    private static void seedTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.txt"))) {
            writer.println(new Transaction("978-0001", "B001", "2025-06-10", "-", "BORROWED").serialize());
            writer.println(new Transaction("978-0002", "B002", "2025-06-15", "-", "BORROWED").serialize());
            writer.println(new Transaction("978-0003", "B003", "2025-06-01", "2025-06-18", "RETURNED").serialize());
            writer.println(new Transaction("978-0004", "B004", "2025-06-05", "-", "BORROWED").serialize());
            writer.println(new Transaction("978-0005", "B001", "2025-06-03", "2025-06-14", "RETURNED").serialize());
        } catch (Exception e) {
            System.out.println("Failed to seed transactions: " + e.getMessage());
        }
    }
}
