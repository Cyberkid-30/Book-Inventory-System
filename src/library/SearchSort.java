package library;

import java.util.*;

public class SearchSort {

    // Linear Search by title, author, or ISBN
    public static List<Book> linearSearch(List<Book> books, String keyword) {
        List<Book> results = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword) ||
                    book.author.toLowerCase().contains(keyword) ||
                    book.isbn.toLowerCase().equals(keyword)) {
                results.add(book);
            }
        }
        return results;
    }

    // Selection Sort by title
    public static void selectionSortByTitle(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(j).title.compareToIgnoreCase(books.get(minIndex).title) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(books, i, minIndex);
        }
    }

    // Merge Sort by year (oldest to newest)
    public static void mergeSortByYear(List<Book> books) {
        if (books.size() <= 1) return;

        int mid = books.size() / 2;
        List<Book> left = new ArrayList<>(books.subList(0, mid));
        List<Book> right = new ArrayList<>(books.subList(mid, books.size()));

        mergeSortByYear(left);
        mergeSortByYear(right);

        merge(left, right, books);
    }

    private static void merge(List<Book> left, List<Book> right, List<Book> merged) {
        merged.clear();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            int y1 = Integer.parseInt(left.get(i).year);
            int y2 = Integer.parseInt(right.get(j).year);
            if (y1 <= y2) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
    }
}
