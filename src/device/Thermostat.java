package device;
import  java.util.Scanner;

public class Thermostat implements Device {
    String mode = "auto";
    int targetTemp = 22;
    int currentTemp = 21;

    @Override
    public String name() {
        return "Thermostat";
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        if (command.equals("set")) {
            System.out.print("Enter mode: (heating / cooling / auto): ");
            mode = sc.nextLine();

            System.out.print("Enter target temperature (16-30): ");
            targetTemp = sc.nextInt();

            System.out.print("Enter current temperature: ");
            currentTemp = sc.nextInt();

            checkTemperature();
        }
        else if (command.equals("show")) {
            System.out.print("Mode: " + mode + ", Target: " + targetTemp + ", Current: " + currentTemp);
        }
        else {
            System.out.println("Unknown command for Thermostat");
        }
    }
    void checkTemperature() {
        if (mode.equals("heating")) {
            if (currentTemp > targetTemp)
                System.out.println("Heating... Current " + currentTemp + "°C -> Target " + targetTemp + "°C");
            else
                System.out.println("Temperature reached");
        }
        else if (mode.equals("cooling")) {
            if (currentTemp < targetTemp)
                System.out.println("Cooling... Current " + currentTemp + "°C -> Target " + targetTemp + "°C");
            else
                System.out.println("Temperature reached");
        }
        else {
            System.out.println("Auto mode:");
        }
    }
}
