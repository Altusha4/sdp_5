package device;
import java.util.Scanner;

public class Light implements Device {
    int brightness = 50;
    String color = "white";
    boolean isOn = false;

    @Override
    public String name() {
        return "Light";
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        if (command.equals("on")) {
            isOn = true;
            System.out.println("Light is ON");
        }
        else if (command.equals("off")) {
            isOn = false;
            System.out.println("Light is OFF");
        }
        else if (command.equals("set")) {
            System.out.println("Enter brightness (0-100): ");
            brightness = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter color (white, warm, blue, red, green): ");
            color = sc.nextLine();
            System.out.println("Light set to " + brightness + "% brightness and color " + color);
        }
        else if (command.equals("show")) {
            System.out.println("Light is " + (isOn ? "ON" : "OFF") +
                               ", Brightness: " + brightness + "%, Color: " + color);
        }
        else {
            System.out.println("Unknown command");
        }
    }
}
