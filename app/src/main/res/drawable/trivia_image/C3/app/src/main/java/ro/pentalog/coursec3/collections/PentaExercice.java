//package ro.pentalog.coursec3.collections;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;
//
//import ro.pentalog.coursec3.inheritance.Student;
//
//public class PentaExercice {
//    private static HashMap<String,Integer> hMap;
//    public static void main(String[] args) {
//        ReadText();
//        Retur();
//    }
//
//    private static void ReadText() {
//        Scanner input = new Scanner(System.in);
//        String text = input.nextLine();
//        int start = 0;
//        int value = 0;
//        for (int j = 0; j < text.length(); j++) {
//            if (text.charAt(j)==' ') {
//                hMap.put(text.substring(start, j),value);
//                start = j + 1;
//                value += 1;
//            }
//        }
//    }
//    private static void Retur(){
//        Set set = hMap.entrySet();
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            System.out.println("key is: " + entry.getKey() + " & Value is: " + entry.getValue());
//        }
//    }
//}
