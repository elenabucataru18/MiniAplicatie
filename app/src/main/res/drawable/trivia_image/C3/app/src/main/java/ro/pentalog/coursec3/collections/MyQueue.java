package ro.pentalog.coursec3.collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A Queue is a collection for holding elements prior to processing
 * Deque interface is a subtype of the java.util.Queue interface. The Deque is related to the double-ended queue that supports addition or removal of elements
 */
public class MyQueue {
    private static Queue queueA;

    public static void main(String[] args) {
        populate();

        printByIterator();

        printByFor();

        firstElement();

        get();

        search("element 5");

        convertToQueue();

        populateDeque();

        operationsWithDeque();
    }

    private static void populate() {
        queueA = new LinkedList();

        queueA.add("element 1");
        queueA.add("element 2");
        queueA.add("element 3");
        queueA.add("element 4");
        queueA.add("element 5");
    }

    /**
     * Access via Iterator
     */
    private static void printByIterator() {
        Iterator iterator = queueA.iterator();
        System.out.println("\n");
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            System.out.print(element + ", ");
        }
    }

    /**
     * Access via new for-loop
     */
    private static void printByFor() {
        System.out.println("\n");
        for (Object object : queueA) {
            String element = (String) object;
            System.out.print(element + ", ");
        }
    }

    private static void firstElement() {
        Object firstElement = queueA.element();
        System.out.println("\n\nFirst element= " + firstElement.toString());

        System.out.println("Push out " + firstElement + " from the queue ");
        Object firstElementRemove = queueA.remove();
        System.out.println("First element remove= " + firstElementRemove.toString());
        System.out.println("and the new head is now: " + queueA.element());

        Object head = queueA.poll();    // return element
        System.out.print("Push out " + head + " from the queue, ");
        System.out.println("and the new head is now: " + queueA.peek());

        System.out.println("Queue: " + queueA.toString());
    }

    /**
     * peek() just returns the current element in the queue, null if empty
     */
    private static void get() {
        System.out.println("\nRetrieve element= " + queueA.peek());
    }

    /**
     * Find out if the queue contains an object
     *
     * @param search
     */
    private static void search(String search) {
        System.out.println("\nDoes the queue contain " + search + "? =>" + queueA.contains(search));
        System.out.println("Does the queue contain " + search.substring(0, 1) + "? =>" + queueA.contains(search.substring(0, 1)));
    }

    /**
     * new Queue instance from another collection
     */
    private static void convertToQueue() {
        java.util.List<String> listNames = Arrays.asList("Alice", "Bob", "Cole", "Dale", "Eric", "Frank");
        Queue<String> queueNames = new LinkedList<>(listNames);
        System.out.println("\nqueueNames= " + queueNames);
    }

    /**
     * Adds an element to the head and an element to the tail of a double ended queue
     */
    private static void populateDeque() {
        Deque<String> queueNames = new ArrayDeque<>();

        queueNames.offer("Katherine");  // insert an element
        queueNames.offer("Bob");

        queueNames.addFirst("Jim");
        queueNames.addLast("Tom");

        System.out.println("\nDeque= " + queueNames);
    }

    /**
     * Removes the head element and tail element from a deque
     */
    private static void operationsWithDeque(){
        Deque<String> queueCustomers = new ArrayDeque<>();

        queueCustomers.offer("Bill");
        queueCustomers.offer("Kim");
        queueCustomers.offer("Lee");
        queueCustomers.offer("Peter");
        queueCustomers.offer("Sam");

        System.out.println("\nQueue before: " + queueCustomers);
        System.out.println("First comes: " + queueCustomers.pollFirst());
        System.out.println("Last comes: " + queueCustomers.pollLast());
        System.out.println("Queue after: " + queueCustomers);
    }
}