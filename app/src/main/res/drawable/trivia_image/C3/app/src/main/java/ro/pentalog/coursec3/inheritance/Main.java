package ro.pentalog.coursec3.inheritance;


public class Main {
    public static void main(String[] args){
        Student student = new Student("John",20);
        student.note = 8;
        System.out.println(student);

        Student otherStudent = new Student("Popescu", 18, 9);
        System.out.println(otherStudent);
    }
}