package Sandbox.ClassVariables;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class ClassVariables {
    public static int x = 7;
    public int y = 3;

    public static void main(String... args) {
        ClassVariables a = new ClassVariables();
        ClassVariables b = new ClassVariables();

        a.y = 5;
        b.y = 6;
        a.x = 1;
        b.x = 2;

        System.out.println("a.y = " + a.y);
        System.out.println("b.y = " + b.y);
        System.out.println("a.x = " + a.x);  // x is static so already set to 2!
        System.out.println("b.x = " + b.x);
        System.out.println("ClassVariables.x = " + ClassVariables.x);
    }
}
