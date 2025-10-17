package app;
import java.util.Scanner;

public class ConsoleIO {
    private static final Scanner sc = new Scanner(System.in);
    public static String ask(String text) {
        System.out.print(text);
        return sc.nextLine().trim();
    }
    public static int askInt(String text) {
        while (true) {
            System.out.print(text);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
    public static double askDouble(String text) {
        while (true) {
            System.out.print(text);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
    public static void close() {
        sc.close();
    }
}
