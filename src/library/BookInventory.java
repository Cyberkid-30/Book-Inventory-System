package library;

import java.util.*;
import java.io.*;

public class BookInventory {
    private HashMap<String, ArrayList<Book>> booksByCategory;
    private final String FILE_NAME = "books.txt";

    public BookInventory() {
        booksByCategory = new HashMap<>();
        loadBooks();
    }

    public void addBook(Book book) {
        booksByCategory.computeIfAbsent(book.category, k -> new ArrayList<>()).add(book);
        saveBooks();
        System.out.println("Book added successfully.");
    }

    public void removeBook(String isbn) {
        for (ArrayList<Book> bookList : booksByCategory.values()) {
            Iterator<Book> it = bookList.iterator();
            while (it.hasNext()) {
                Book book = it.next();
                if (book.isbn.equals(isbn)) {
                    it.remove();
                    saveBooks();
                    System.out.println("Book removed successfully.");
                    return;
                }
            }
        }
        System.out.println("Book with ISBN not found.");
    }

    public void listAllBooks() {
        System.out.println("\nAll Books in Inventory:");
        for (ArrayList<Book> books : booksByCategory.values()) {
            for (Book book : books) {
                System.out.println(" - " + book);
            }
        }
    }

    public void listByCategory(String category) {
        ArrayList<Book> books = booksByCategory.get(category);
        if (books == null || books.isEmpty()) {
            System.out.println("No books found in category: " + category);
            return;
        }
        System.out.println("\nBooks in category '" + category + "':");
        for (Book book : books) {
            System.out.println(" - " + book);
        }
    }

    private void loadBooks() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Book book = Book.deserialize(line);
                if (book != null) {
                    booksByCategory.computeIfAbsent(book.category, k -> new ArrayList<>()).add(book);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Failed to load books: " + e.getMessage());
        }
    }

    private void saveBooks() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (ArrayList<Book> books : booksByCategory.values()) {
                for (Book book : books) {
                    writer.println(book.serialize());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save books: " + e.getMessage());
        }
    }

    public List<Book> getAllBooksFlat() {
        List<Book> all = new ArrayList<>();
        for (ArrayList<Book> books : booksByCategory.values()) {
            all.addAll(books);
        }
        return all;
    }

}
