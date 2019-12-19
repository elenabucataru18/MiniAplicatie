package ro.pentalog.coursec3.polymorphism;

public class Bicycle {
    private String color;

    public Bicycle(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void printDescription() {
        System.out.println("\nBike has color " + this.color + ". ");
    }
}


