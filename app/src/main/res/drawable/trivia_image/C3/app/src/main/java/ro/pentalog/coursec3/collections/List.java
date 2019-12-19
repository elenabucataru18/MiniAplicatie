package ro.pentalog.coursec3.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import ro.pentalog.coursec3.inheritance.Student;

/**
 * List = An ordered collection (also known as a sequence).
 */
public class List {
    private static java.util.List<Student> students;
    private static java.util.List<String> listStrings;

    public static void main(String[] args) {
//        populate();
        populateWithElementAndList();
        print();

        retrieveElements(3);

        search("android");

        sort();

        shuffling();

        reverse();

        subList();

        listToArray();
    }

    /**
     * Create list and populate
     */
    private static void populate() {
        students = new ArrayList<>();
        students.add(new Student("Student 1", 10));
        students.add(new Student("Student 2", 17, 8));
        students.add(new Student("Student 3", 18, 9));
    }

    /**
     * Create list and populate
     */
    private static void populateWithElementAndList() {
        students = new ArrayList<>();
        students.add(new Student("Student 1", 10));
        students.add(new Student("Student 2", 17, 8));
        students.add(new Student("Student 3", 18, 9));

        java.util.List<Student> students2 = new ArrayList<>();
        students.add(new Student("Student 1'", 20));
        students.add(new Student("Student 2'", 21, 5));
        students.add(new Student("Student 3'", 22, 6));

        students.addAll(students2);
        students.add(new Student("Student 4", 11, 7));
    }

    /**
     * Indexed Collections
     */
    private static void print() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }

        System.out.println("\n");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Iteration and removing elements
     */
    private static void iterator() {
        for (Iterator it = students.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
            it.remove();
        }

        System.out.println("students= " + students);
    }

    /**
     * Iteration and set property
     */
    private static void iteratorWithFor() {
        for (Student student : students) {
            student.setNote(10);
        }
        System.out.println("students= " + students);
    }

    /**
     * Print element for a specific index
     *
     * @param index
     */
    private static void retrieveElements(int index) {
        if (students != null && !students.isEmpty()) {
            if (students.size() > index) {
                System.out.println("\nstudents[" + index + "]= " + students.get(index));
            }
        }
    }

    private static void search(String word) {
        listStrings = new ArrayList<>();
        listStrings.add("letter");
        listStrings.add("android");
        listStrings.add("phone");
        listStrings.add("java");
        System.out.println("\n" + "listStrings= " + listStrings);

        if (listStrings.contains(word)) {
            System.out.println("Found the element: " + word);
        } else {
            System.out.println("There is no such element");
        }


        int indexOfWord = listStrings.indexOf(word);
        System.out.println("indexOfWord= " + indexOfWord);
    }

    /**
     * Sort elements in a list
     */
    private static void sort() {
        java.util.List<String> listStrings = new ArrayList<>();
        listStrings.add("D");
        listStrings.add("C");
        listStrings.add("E");
        listStrings.add("A");
        listStrings.add("B");

        System.out.println("\nlistStrings before sorting: " + listStrings);

        Collections.sort(listStrings);

        System.out.println("listStrings after sorting: " + listStrings);
    }

    /**
     * To randomly permute elements in a list
     */
    private static void shuffling() {
        java.util.List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            numbers.add(i);
        }

        System.out.println("\nList before shuffling: " + numbers);

        Collections.shuffle(numbers);

        System.out.println("List after shuffling: " + numbers);
    }

    /**
     * To reverse order of elements in a list
     */
    private static void reverse() {
        java.util.List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 10; i++)
            numbers.add(i);

        System.out.println("\nList before reversing: " + numbers);

        Collections.reverse(numbers);

        System.out.println("List after reversing: " + numbers);
    }

    /**
     * Get a portion of the list between the specified fromIndex (inclusive) and toIndex (exclusive)
     */
    private static void subList() {
        java.util.List<String> listNames = Arrays.asList("Tom", "John", "Mary", "Peter", "David", "Alice");
        System.out.println("\nOriginal list: " + listNames);

        java.util.List<String> subList = listNames.subList(2, 5);
        System.out.println("Sub list (2,5): " + subList);
    }

    /**
     * Converting between Lists and arrays
     */
    private static void listToArray() {
        java.util.List<String> listNames = Arrays.asList("John", "Peter", "Tom", "Mary", "David", "Sam");

        java.util.List<Integer> listNumbers = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8);

        System.out.println("\n" + "listNames= " + listNames);
        System.out.println("listNumbers= " + listNumbers);
    }
}
