package app;
import java.util.Scanner;

public class ConsoleIO {
    private static final Scanner sc = new Scanner(System.in);

    public static String ask(String text) {
        System.out.print(text);
        return sc.nextLine();
    }

    public static int askInt(String text) {
        System.out.print(text);
        return sc.nextInt();
    }
}
