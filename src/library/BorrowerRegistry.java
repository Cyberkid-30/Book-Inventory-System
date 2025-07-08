package library;

import java.io.*;
import java.util.*;

public class BorrowerRegistry {
    private Map<String, Borrower> borrowers;
    private final String FILE_NAME = "borrowers.txt";

    public BorrowerRegistry() {
        borrowers = new HashMap<>();
        loadBorrowers();
    }

    public void addBorrower(Borrower b) {
        if (borrowers.containsKey(b.id)) {
            System.out.println("Borrower with ID already exists.");
            return;
        }
        borrowers.put(b.id, b);
        saveBorrowers();
        System.out.println("Borrower added.");
    }

    public void removeBorrower(String id) {
        if (borrowers.remove(id) != null) {
            saveBorrowers();
            System.out.println("Borrower removed.");
        } else {
            System.out.println("Borrower ID not found.");
        }
    }

    public void viewAll() {
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers found.");
            return;
        }
        for (Borrower b : borrowers.values()) {
            System.out.println(" - " + b);
        }
    }

    public Borrower searchBorrower(String id) {
        return recursiveSearch(new ArrayList<>(borrowers.values()), 0, id);
    }

    private Borrower recursiveSearch(List<Borrower> list, int index, String id) {
        if (index >= list.size()) return null;
        if (list.get(index).id.equals(id)) return list.get(index);
        return recursiveSearch(list, index + 1, id);
    }

    private void loadBorrowers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Borrower b = Borrower.deserialize(line);
                if (b != null) {
                    borrowers.put(b.id, b);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading borrowers: " + e.getMessage());
        }
    }

    private void saveBorrowers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Borrower b : borrowers.values()) {
                writer.println(b.serialize());
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowers: " + e.getMessage());
        }
    }

    public void updateBorrower(Borrower b) {
        borrowers.put(b.id, b);
        saveBorrowers();
    }

    public List<Borrower> getAllBorrowers() {
        return new ArrayList<>(borrowers.values());
    }

}
