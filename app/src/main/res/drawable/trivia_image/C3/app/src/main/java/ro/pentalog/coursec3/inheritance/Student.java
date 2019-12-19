package ro.pentalog.coursec3.inheritance;



public class Student extends Person {
    // Person is a superclass of Student
    // Student is a subclass of Person

    public double note;

    public Student() {
        super("", 0);
    }

    public Student(String name, int age) {
        super(name, age);
    }

    public Student(String name, int age, double note) {
        super(name, age);
        this.note = note;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "note=" + note +
                '}';
    }
}



