package src.HashTable;

import src.Business.Business;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private final int size;
    private final LinkedList<Business>[] tableWithLinkedLists;
    private final List<Business>[] tableWithArrayLists;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        tableWithLinkedLists = new LinkedList[size];
        tableWithArrayLists = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            tableWithLinkedLists[i] = new LinkedList<>();
            tableWithArrayLists[i] = new ArrayList<>();
        }
    }

    private int hashFunctionMultiplication(String key) {
        int hashCode = key.hashCode();
        double A = (Math.sqrt(5) - 1) / 2;
        return (int) (Math.abs(hashCode * A) % 1 * size);
    }

    private int hashFunctionDivision(String key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    public void insert(Business business, boolean useMultiplication, boolean useLinkedList) {
        String key = business.getId();
        int hashIndex = useMultiplication ? hashFunctionMultiplication(key) : hashFunctionDivision(key);
        if (useLinkedList) {
            tableWithLinkedLists[hashIndex].add(business);
        } else {
            tableWithArrayLists[hashIndex].add(business);
        }
    }

    public Business search(String id, boolean useMultiplication, boolean useLinkedList) {
        int hashIndex = useMultiplication ? hashFunctionMultiplication(id) : hashFunctionDivision(id);
        List<Business> list = useLinkedList ? tableWithLinkedLists[hashIndex] : tableWithArrayLists[hashIndex];
        for (Business business : list) {
            if (business.getId().equals(id)) {
                return business;
            }
        }
        return null;
    }

    public void printCollisions(boolean useLinkedList) {
        for (int i = 0; i < 100; i++) {
            List<Business> businesses = useLinkedList ? tableWithLinkedLists[i] : tableWithArrayLists[i];
            if (businesses.size() > 1) {
                System.out.println("Indice: " + i + " tiene " + (businesses.size() - 1) + " colisiones.");
                for (Business business : businesses) {
                    System.out.println("    - " + business);
                }
            }
        }
    }
}
