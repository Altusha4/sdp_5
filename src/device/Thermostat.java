package device;
import java.util.Scanner;

public class Thermostat implements Device {
    String mode = "auto";
    double targetTemp = 22.0;
    double currentTemp = 21.0;
    boolean enabled = true;

    @Override
    public String name() {
        return "Thermostat";
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        switch (command) {
            case "on":
                enabled = true;
                System.out.println("Thermostat is now ON");
                break;
            case "off":
                enabled = false;
                System.out.println("Thermostat is now OFF");
                break;
            case "set":
                if (!enabled) {
                    System.out.println("Thermostat is OFF. Turn it ON first.");
                    break;
                }
                System.out.println("\n--- THERMOSTAT SETTINGS ---");
                System.out.println("Available modes: heating / cooling / auto");
                System.out.print("Enter mode: ");
                String m = sc.nextLine().trim().toLowerCase();

                if (!m.equals("heating") && !m.equals("cooling") && !m.equals("auto")) {
                    System.out.println("Invalid mode. Using previous: " + mode);
                } else {
                    mode = m;
                }
                System.out.print("Enter target temperature (16–30 °C): ");
                try {
                    targetTemp = Double.parseDouble(sc.nextLine());
                    if (targetTemp < 16 || targetTemp > 30) {
                        System.out.println("Out of range! Setting default 22°C.");
                        targetTemp = 22;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Keeping previous temperature.");
                }
                System.out.print("Enter current temperature: ");
                try {
                    currentTemp = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Keeping previous value.");
                }
                checkTemperature();
                break;
            case "show":
                System.out.printf("Mode: %s | Target: %.1f°C | Current: %.1f°C | Power: %s%n",
                        mode, targetTemp, currentTemp, enabled ? "ON" : "OFF");
                break;
            case "help":
                System.out.println("""
            Commands for Thermostat:
              on           - power on
              off          - power off
              set          - interactive mode and temperatures
              show         - show status
              help         - show this help
            """);
                break;
            default:
                System.out.println("Unknown command for Thermostat. Type 'help'");
        }
    }
    void checkTemperature() {
        if (!enabled) {
            System.out.println("Thermostat is OFF. No adjustment.");
            return;
        }

        switch (mode) {
            case "heating" -> {
                if (currentTemp < targetTemp)
                    System.out.printf("Heating... Current %.1f°C → Target %.1f°C%n", currentTemp, targetTemp);
                else
                    System.out.println("Room warm enough. Heating stopped.");
            }
            case "cooling" -> {
                if (currentTemp > targetTemp)
                    System.out.printf("❄Cooling... Current %.1f°C → Target %.1f°C%n", currentTemp, targetTemp);
                else
                    System.out.println("Room cool enough. Cooling stopped.");
            }
            default -> System.out.println("Auto mode active — adjusting automatically.");
        }
    }
}
