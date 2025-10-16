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

        switch (command) {
            case "on":
                isOn =  true;
                System.out.println("Light is ON");
                break;
            case "off":
                isOn =  false;
                System.out.println("Light is OFF");
                break;
            case "show":
                System.out.println("Light is " + (isOn ? "ON" : "OFF")
                        + ", Brightness: " + brightness + "%, Color: " + color);
                break;
            case "set":
                System.out.print("Enter brightness (0-100): ");
                brightness = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter color (white, warm, blue, red, green): ");
                color = sc.nextLine();
                System.out.println("Light set to " + brightness + "% and color " + color);
            default:
                System.out.println("Unknown command for Light");
        }
    }
}

