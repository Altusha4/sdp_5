package device;

public interface Device {
    String name(); //"Light", "Thermostat" and ...
    void operate(String command);
}