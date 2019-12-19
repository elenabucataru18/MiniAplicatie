package ro.pentalog.coursec3.superpackage;


public class Subclass  extends Superclass{
    // overrides printMethod in Superclass
    public void printMethod() {
        super.printMethod();
        System.out.println("Printed in Subclass");
    }
}

