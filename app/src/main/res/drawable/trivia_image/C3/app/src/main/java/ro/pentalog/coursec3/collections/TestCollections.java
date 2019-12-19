package ro.pentalog.coursec3.collections;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestCollections {
    private static final int N = 100000;

    private static void testAdd(java.util.List lst) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            lst.add(new Integer(i));
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Add: " + (t2 - t1));
    }

    private static void testGet(java.util.List lst) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            lst.get(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Get: " + (t2 - t1));
    }

    private static void testRemove(java.util.List lst) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            lst.remove(0);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Remove : " + (t2 - t1) + "\n");
    }

    public static void main(String args[]) {
        java.util.List list = new ArrayList<>();
        System.out.println("List");
        testAdd(list);
        testGet(list);
        testRemove(list);

        System.out.println("LinkedList");
        java.util.List linkedList = new LinkedList();
        testAdd(linkedList);
        testGet(linkedList);
        testRemove(linkedList);
    }
}