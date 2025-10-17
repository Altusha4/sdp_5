package device;

import app.ConsoleIO;

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
        switch (command) {
            case "on" -> {
                enabled = true;
                ConsoleIO.println("Thermostat is now ON");
            }
            case "off" -> {
                enabled = false;
                ConsoleIO.println("Thermostat is now OFF");
            }
            case "set" -> {
                if (!enabled) {
                    ConsoleIO.println("Thermostat is OFF. Turn it ON first.");
                    return;
                }

                ConsoleIO.line();
                ConsoleIO.println("--- THERMOSTAT SETTINGS ---");
                ConsoleIO.println("Available modes: heating / cooling / auto");
                String m = ConsoleIO.ask("Enter mode: ").toLowerCase();

                if (!m.equals("heating") && !m.equals("cooling") && !m.equals("auto")) {
                    ConsoleIO.println("Invalid mode. Using previous: " + mode);
                } else {
                    mode = m;
                }

                double t = ConsoleIO.askDouble("Enter target temperature (16–30 °C): ");
                if (t < 16 || t > 30) {
                    ConsoleIO.println("Out of range! Setting default 22°C.");
                    targetTemp = 22;
                } else {
                    targetTemp = t;
                }

                double c = ConsoleIO.askDouble("Enter current temperature: ");
                currentTemp = c;

                checkTemperature();
            }
            case "show" -> ConsoleIO.println(String.format(
                    "Mode: %s | Target: %.1f°C | Current: %.1f°C | Power: %s",
                    mode, targetTemp, currentTemp, enabled ? "ON" : "OFF"));
            case "help" -> ConsoleIO.println("""
                Commands for Thermostat:
                  on           - power on
                  off          - power off
                  set          - interactive mode and temperatures
                  show         - show status
                  help         - show this help
                """);
            default -> ConsoleIO.println("Unknown command for Thermostat. Type 'help'");
        }
    }
    private void checkTemperature() {
        if (!enabled) {
            ConsoleIO.println("Thermostat is OFF. No adjustment.");
            return;
        }
        switch (mode) {
            case "heating" -> {
                if (currentTemp < targetTemp)
                    ConsoleIO.println(String.format("Heating... Current %.1f°C → Target %.1f°C",
                            currentTemp, targetTemp));
                else
                    ConsoleIO.println("Room warm enough. Heating stopped.");
            }
            case "cooling" -> {
                if (currentTemp > targetTemp)
                    ConsoleIO.println(String.format("❄ Cooling... Current %.1f°C → Target %.1f°C",
                            currentTemp, targetTemp));
                else
                    ConsoleIO.println("Room cool enough. Cooling stopped.");
            }
            default -> ConsoleIO.println("Auto mode active — adjusting automatically.");
        }
    }
}
