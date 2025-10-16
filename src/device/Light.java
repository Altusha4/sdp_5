package device;
import java.util.Scanner;

public class Light implements Device {
    int brightness = 50;
    String color = "white";
    boolean isOn = false;

    private static final String[] ALLOWED_COLORS = {"white","warm","blue","red","green"};

    @Override
    public String name() { return "Light"; }

    private boolean isAllowedColor(String c) {
        for (String s : ALLOWED_COLORS) if (s.equalsIgnoreCase(c)) return true;
        return false;
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        switch (command) {
            case "on":
                isOn = true;
                System.out.println("Light is ON");
                break;
            case "off":
                isOn = false;
                System.out.println("Light is OFF");
                break;
            case "set": {
                while (true) {
                    System.out.print("Enter brightness 0..100 (or 'back'): ");
                    String in = sc.nextLine().trim();
                    if (in.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    try {
                        int b = Integer.parseInt(in);
                        if (b < 0 || b > 100) {
                            System.out.println("Brightness must be between 0 and 100.");
                            continue;
                        }
                        brightness = b;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a number 0..100.");
                    }
                }
                while (true) {
                    System.out.print("Enter color " +
                            "(white, warm, blue, red, green) or 'back': ");
                    String c = sc.nextLine().trim();
                    if (c.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    if (!isAllowedColor(c)) {
                        System.out.println("No such color. Try again.");
                        continue;
                    }
                    color = c.toLowerCase();
                    break;
                }
                System.out.println("Light set to " + brightness + "% and color " + color);
                break;
            }
            case "show":
                System.out.println("Light is " + (isOn ? "ON" : "OFF")
                        + ", Brightness: " + brightness + "%, Color: " + color);
                break;
            default:
                System.out.println("Unknown command for Light");
        }
    }
}
