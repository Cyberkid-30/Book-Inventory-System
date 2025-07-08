package library;

import java.util.*;

public class Borrower {
    public String name;
    public String id;
    public String contact;
    public double fine;
    public List<String> borrowedIsbns;

    public Borrower(String name, String id, String contact, double fine, List<String> borrowedIsbns) {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.fine = fine;
        this.borrowedIsbns = borrowedIsbns;
    }

    public String serialize() {
        return String.join("||", name, id, contact, String.valueOf(fine),
                String.join(",", borrowedIsbns));
    }

    public static Borrower deserialize(String line) {
        String[] parts = line.split("\\|\\|");
        if (parts.length != 5) return null;

        String[] isbns = parts[4].isEmpty() ? new String[]{} : parts[4].split(",");
        return new Borrower(parts[0], parts[1], parts[2],
                Double.parseDouble(parts[3]), new ArrayList<>(Arrays.asList(isbns)));
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %s) | Contact: %s | Fine: $%.2f | Borrowed Books: %s",
                name, id, contact, fine, borrowedIsbns.toString());
    }
}
