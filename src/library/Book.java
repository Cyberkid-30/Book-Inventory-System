package library;

public class Book {
    public String title;
    public String author;
    public String isbn;
    public String category;
    public String year;
    public String publisher;
    public String shelfLocation;

    public Book(String title, String author, String isbn, String category,
                String year, String publisher, String shelfLocation) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.year = year;
        this.publisher = publisher;
        this.shelfLocation = shelfLocation;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%s) [ISBN: %s] - Shelf: %s",
                title, author, year, isbn, shelfLocation);
    }

    public String serialize() {
        return String.join("||", title, author, isbn, category, year, publisher, shelfLocation);
    }

    public static Book deserialize(String line) {
        String[] parts = line.split("\\|\\|");
        if (parts.length != 7) return null;
        return new Book(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }
}
