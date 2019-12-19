package ro.pentalog.coursec3.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * HashMap = Hash table based implementation of the Map interface = is a collection of key-value pairs
 *
 *
 */
public class MyHashMap {
    private static HashMap<Integer, String> hMap;

    public static void main(String[] args) {
        populate();

        iterator();
        iterateThroughKeySet();

        getValueFromIndex(2);

        removeFromAKey(3);

        search("Romania");

        sort();

        addDuplicatKey(12);

        addDuplicatKey(1);

        clearAllValue();
    }

    private static void populate() {
        hMap = new HashMap<>();
        hMap.put(12, "Romania");
        hMap.put(2, "China");
        hMap.put(7, "Bulgaria");
        hMap.put(49, "Budapesta");
        hMap.put(3, "Londra");

        System.out.println("\n hMap= " + hMap + "\n");
    }

    /**
     * Display content using Iterator
     */
    private static void iterator() {
        Set set = hMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("key is: " + entry.getKey() + " & Value is: " + entry.getValue());
        }
    }

    /**
     * Iterating over entries using a For-Each loop
     */
    private static void iterateThroughKeySet() {
        System.out.println("\n");
        for (Map.Entry<Integer, String> entry : hMap.entrySet()) {
            System.out.println("key is: " + entry.getKey() + " & Value is: " + entry.getValue());
        }
    }

    private static void getValueFromIndex(int index) {
        if (hMap.size() > index) {
            String value = hMap.get(2);
            System.out.println("\nValue at index " + index + " is: " + value);
        }
    }

    private static void removeFromAKey(int key) {
        if (hMap.containsKey(key)) {
            hMap.remove(key);
            System.out.println("\nMap key and values after removal:");
        } else {
            System.out.println("don't exist key " + key);
            return;
        }
        iterator();
    }

    /**
     * Check exist value or key
     */
    private static void search(String search) {
        System.out.println("\nDoes HashMap contains " + search + " as key: " + hMap.containsKey(search));
        System.out.println("Does HashMap contains " + search + " as value: " + hMap.containsValue(search));
        System.out.println("Is HashMap is empty: " + hMap.isEmpty());
    }

    /**
     * HashMap is an unsorted Map in Java, neither key or value is sorted
     * So any sorting for Map requires SortedMap for sorting on either key or value
     */
    private static void sort() {
        System.out.println("\nUnsorted HashMap: " + hMap);
        TreeMap sortedHashMap = new TreeMap(hMap);
        System.out.println("Sorted HashMap: " + sortedHashMap);
    }

    /**
     * Try add duplicate key
     */
    private static void addDuplicatKey(Integer key) {
        System.out.println("\nShow that duplicates cannot be added.");
        Object value = hMap.put(key, "Moldova");
        if (value != null)
            System.out.println("Could not add " + key);
        else
            System.out.println("Added " + key);
        System.out.println("map = " + hMap);
    }

    /**
     * Remove all elements
     */
    private static void clearAllValue() {
        System.out.println("\nSize of Map: " + hMap.size());
        hMap.clear();
        System.out.println("Size of Map: " + hMap.size());
    }
}