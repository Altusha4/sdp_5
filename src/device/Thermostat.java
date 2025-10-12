package device;

public class Thermostat implements Device {
    @Override
    public void operate(String command) {
        System.out.println("device.Thermostat: " + command);
    }
}
