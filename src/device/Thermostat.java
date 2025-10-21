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
        switch (command.trim().toLowerCase()) {
            case "on" -> setEnabled(true);
            case "off" -> setEnabled(false);
            case "set" -> configureSettings();
            case "show" -> showStatus();
            case "help" -> showHelp();
            default -> ConsoleIO.println("Unknown command for Thermostat. Type 'help'");
        }
    }
    private void setEnabled(boolean status) {
        enabled = status;
        ConsoleIO.println("Thermostat is now " + (enabled ? "ON" : "OFF"));
    }
    private void configureSettings() {
        if (!enabled) {
            ConsoleIO.println("Thermostat is OFF. Turn it ON first.");
            return;
        }
        ConsoleIO.line();
        ConsoleIO.println("--- THERMOSTAT SETTINGS ---");
        mode = ConsoleIO.ask("Enter mode (heating/cooling/auto): ").toLowerCase();
        if (!mode.matches("heating|cooling|auto")) {
            ConsoleIO.println("Invalid mode. Using previous: " + mode);
        }
        targetTemp = getTemperature("Enter target temperature (16–30 °C): ", 16, 30, 22);
        currentTemp = ConsoleIO.askDouble("Enter current temperature: ");
        checkTemperature();
    }
    private double getTemperature(String prompt, double min, double max, double defaultValue) {
        double temp = ConsoleIO.askDouble(prompt);
        if (temp < min || temp > max) {
            ConsoleIO.println("Out of range! Setting default " + defaultValue + "°C.");
            return defaultValue;
        }
        return temp;
    }
    private void showStatus() {
        ConsoleIO.println(String.format(
                "Mode: %s | Target: %.1f°C | Current: %.1f°C | Power: %s",
                mode, targetTemp, currentTemp, enabled ? "ON" : "OFF"));
    }
    private void showHelp() {
        ConsoleIO.println("""
                Commands for Thermostat:
                  on           - power on
                  off          - power off
                  set          - interactive mode and temperatures
                  show         - show status
                  help         - show this help
                """);
    }
    private void checkTemperature() {
        if (!enabled) {
            ConsoleIO.println("Thermostat is OFF. No adjustment.");
            return;
        }
        switch (mode) {
            case "heating" -> handleHeating();
            case "cooling" -> handleCooling();
            default -> ConsoleIO.println("Auto mode active — adjusting automatically.");
        }
    }
    private void handleHeating() {
        if (currentTemp < targetTemp) {
            ConsoleIO.println(String.format("Heating... Current %.1f°C → Target %.1f°C", currentTemp, targetTemp));
        } else {
            ConsoleIO.println("Room warm enough. Heating stopped.");
        }
    }
    private void handleCooling() {
        if (currentTemp > targetTemp) {
            ConsoleIO.println(String.format("Cooling... Current %.1f°C → Target %.1f°C", currentTemp, targetTemp));
        } else {
            ConsoleIO.println("Room cool enough. Cooling stopped.");
        }
    }
}
