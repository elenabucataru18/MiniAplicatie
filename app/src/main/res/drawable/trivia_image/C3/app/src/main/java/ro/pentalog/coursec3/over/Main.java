package ro.pentalog.coursec3.over;

public class Main {
    public static void main(String[] args) {

        //overloading
        A a = new A();
        a.hello();
        a.hello("Android");


        //overriding
        B b = new B();
        b.hello();
        b.hello("all");
    }
}