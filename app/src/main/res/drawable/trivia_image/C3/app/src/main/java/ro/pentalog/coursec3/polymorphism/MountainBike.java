package ro.pentalog.coursec3.polymorphism;

public class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(String color, String suspension) {
        super(color);
        this.suspension = suspension;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("MountainBike with" + suspension + " suspensions. ");
    }
}