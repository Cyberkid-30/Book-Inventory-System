package library;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookInventory inventory = new BookInventory();
        BorrowerRegistry registry = new BorrowerRegistry();
        LendingTracker tracker = new LendingTracker();
        OverdueMonitor monitor = new OverdueMonitor(registry);
        ReportGenerator reports = new ReportGenerator(inventory, registry);

        while (true) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("\n=== Ebenezer Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. List All Books");
            System.out.println("4. List Books by Category");
            System.out.println("5. Search Book");
            System.out.println("6. Sort Books by Title (Selection Sort)");
            System.out.println("7. Sort Books by Year (Merge Sort)");
            System.out.println("8. Add Borrower");
            System.out.println("9. Remove Borrower");
            System.out.println("10. View All Borrowers");
            System.out.println("11. Search Borrower by ID");
            System.out.println("12. Borrow Book");
            System.out.println("13. Return Book");
            System.out.println("14. View Borrowed Transactions");
            System.out.println("15. View Returned Transactions");
            System.out.println("16. Check Overdue Books");
            System.out.println("17. Report: Most Borrowed Books");
            System.out.println("18. Report: Top Fined Borrowers");
            System.out.println("19. Report: Inventory by Category");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Year: ");
                    String year = scanner.nextLine();
                    System.out.print("Publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Shelf Location: ");
                    String shelf = scanner.nextLine();
                    Book book = new Book(title, author, isbn, category, year, publisher, shelf);
                    inventory.addBook(book);
                    break;

                case "2":
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = scanner.nextLine();
                    inventory.removeBook(removeIsbn);
                    break;

                case "3":
                    inventory.listAllBooks();
                    break;

                case "4":
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine();
                    inventory.listByCategory(cat);
                    break;

                case "5":
                    System.out.print("Enter keyword (title/author/ISBN): ");
                    String keyword = scanner.nextLine();
                    List<Book> results = SearchSort.linearSearch(inventory.getAllBooksFlat(), keyword);
                    if (results.isEmpty()) {
                        System.out.println("No matching books found.");
                    } else {
                        System.out.println("\nSearch Results:");
                        for (Book b : results) System.out.println(" - " + b);
                    }
                    break;

                case "6":
                    List<Book> sortedByTitle = inventory.getAllBooksFlat();
                    SearchSort.selectionSortByTitle(sortedByTitle);
                    System.out.println("\nBooks Sorted by Title:");
                    for (Book b : sortedByTitle) System.out.println(" - " + b);
                    break;

                case "7":
                    List<Book> sortedByYear = inventory.getAllBooksFlat();
                    SearchSort.mergeSortByYear(sortedByYear);
                    System.out.println("\nBooks Sorted by Year:");
                    for (Book b : sortedByYear) System.out.println(" - " + b);
                    break;

                case "8":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("ID Number: ");
                    String id = scanner.nextLine();
                    System.out.print("Contact Info: ");
                    String contact = scanner.nextLine();
                    Borrower newB = new Borrower(name, id, contact, 0.0, new ArrayList<>());
                    registry.addBorrower(newB);
                    break;

                case "9":
                    System.out.print("Enter ID to remove: ");
                    String removeId = scanner.nextLine();
                    registry.removeBorrower(removeId);
                    break;

                case "10":
                    registry.viewAll();
                    break;

                case "11":
                    System.out.print("Enter ID to search: ");
                    String searchId = scanner.nextLine();
                    Borrower found = registry.searchBorrower(searchId);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Borrower not found.");
                    }
                    break;

                case "12":
                    System.out.print("Borrower ID: ");
                    String borrowerId = scanner.nextLine();
                    System.out.print("Book ISBN: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Borrow Date (YYYY-MM-DD): ");
                    String borrowDate = scanner.nextLine();
                    tracker.borrowBook(borrowIsbn, borrowerId, borrowDate);
                    break;

                case "13":
                    System.out.print("Borrower ID: ");
                    String returnerId = scanner.nextLine();
                    System.out.print("Book ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Return Date (YYYY-MM-DD): ");
                    String returnDate = scanner.nextLine();
                    tracker.returnBook(returnIsbn, returnerId, returnDate);
                    break;

                case "14":
                    tracker.viewBorrowed();
                    break;

                case "15":
                    tracker.viewReturned();
                    break;

                case "16":
                    monitor.checkAndUpdateOverdues();
                    break;

                case "17":
                    reports.mostBorrowedBooks();
                    break;

                case "18":
                    reports.topFinedBorrowers();
                    break;

                case "19":
                    reports.inventoryDistribution();
                    break;

                case "0":
                    System.out.println("Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
