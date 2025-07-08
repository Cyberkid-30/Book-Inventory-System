package library;

public class Transaction {
    public String isbn;
    public String borrowerId;
    public String borrowDate;
    public String returnDate;
    public String status;  // "BORROWED" or "RETURNED"

    public Transaction(String isbn, String borrowerId, String borrowDate, String returnDate, String status) {
        this.isbn = isbn;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String serialize() {
        return String.join("||", isbn, borrowerId, borrowDate, returnDate, status);
    }

    public static Transaction deserialize(String line) {
        String[] parts = line.split("\\|\\|");
        if (parts.length != 5) return null;
        return new Transaction(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Borrower ID: %s | Borrowed: %s | Returned: %s | Status: %s",
                isbn, borrowerId, borrowDate, returnDate, status);
    }
}
