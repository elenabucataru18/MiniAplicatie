package ro.pentalog.coursec3.polymorphism;


public class BikeMain {
    public static void main(String[] args){
        Bicycle bike01, bike02, bike03;

        bike01 = new Bicycle("blue");
        bike02 = new MountainBike("red", "Dual");
        bike03 = new CustomBike("pink", 24);

        bike01.printDescription();
        bike02.printDescription();
        bike03.printDescription();
    }
}