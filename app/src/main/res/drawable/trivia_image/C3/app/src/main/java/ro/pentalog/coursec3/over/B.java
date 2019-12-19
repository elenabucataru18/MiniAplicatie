package ro.pentalog.coursec3.over;

public class B extends A {
    //overriding

    public void hello() {
        super.hello();
        System.out.println("Hi");
    }

    public void hello(String name) {
        System.out.println("Hi " + name);
    }
}
