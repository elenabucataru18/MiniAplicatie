package ro.pentalog.coursec3;


public class VariableArguments {

//    [modifiers] ReturnedType methodName(ArgumentsType... args)

    public static void method(Object... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + "  ");
        }
        System.out.print("\n");
    }


    public static void main(String args[]) {
        method("Hello");
        method("Hello", "Java", 1.8);


        if (args.length < 3) {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }
        String str = args[0];
        int a = Integer.parseInt(args[1]);
        double x = Double.parseDouble(args[2]);
        System.out.println("str= " + str + ",  a= " + a + ",  x= " + x);
    }
}
