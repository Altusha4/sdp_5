package device;
import java.util.Scanner;

public class Thermostat implements Device {
    String mode = "auto";
    double targetTemp = 22.0;
    double currentTemp = 21.0;

    @Override
    public String name() {
        return "Thermostat";
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        switch (command) {
            case "set":
                System.out.print("Enter mode (heating / cooling / auto / back): ");
                String m = sc.nextLine().trim().toLowerCase();
                if (m.equals("back")) {
                    System.out.println("Canceled.");
                    break;
                }
                if (m.equals("heating") || m.equals("cooling") || m.equals("auto")) {
                    mode = m;
                } else {
                    System.out.println("Unknown mode.");
                    break;
                }

                while (true) {
                    System.out.print("Enter target temperature (16–30) or 'back': ");
                    String t = sc.nextLine().trim();
                    if (t.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    try {
                        double val = Double.parseDouble(t);
                        if (val < 16 || val > 30) {
                            System.out.println("Temperature must be between 16 and 30.");
                            continue;
                        }
                        targetTemp = val;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number 16–30.");
                    }
                }

                while (true) {
                    System.out.print("Enter current temperature or 'back': ");
                    String t = sc.nextLine().trim();
                    if (t.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    try {
                        currentTemp = Double.parseDouble(t);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number.");
                    }
                }

                checkTemperature();
                break;

            case "show":
                System.out.printf("Mode: %s, Target: %.1f°C, Current: %.1f°C%n",
                        mode, targetTemp, currentTemp);
                break;

            default:
                System.out.println("Unknown command for Thermostat");
        }
    }

    void checkTemperature() {
        switch (mode) {
            case "heating":
                if (currentTemp < targetTemp)
                    System.out.printf("Heating... Current %.1f°C → Target %.1f°C%n",
                            currentTemp, targetTemp);
                else
                    System.out.println("Target temperature reached or exceeded.");
                break;

            case "cooling":
                if (currentTemp > targetTemp)
                    System.out.printf("Cooling... Current %.1f°C → Target %.1f°C%n",
                            currentTemp, targetTemp);
                else
                    System.out.println("Target temperature reached or below.");
                break;

            default:
                System.out.println("Auto mode active — adjusting automatically.");
        }
    }
}
