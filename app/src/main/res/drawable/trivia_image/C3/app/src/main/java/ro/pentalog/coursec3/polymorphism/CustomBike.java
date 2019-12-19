package ro.pentalog.coursec3.polymorphism;


public class CustomBike extends Bicycle{
    private int speed;

    public CustomBike(String color, int speed) {
        super(color);
        this.speed = speed;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("CustomBike with" + speed + " speed. ");
    }
}