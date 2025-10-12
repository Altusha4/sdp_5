package device;

public class Light implements Device {
    @Override
    public void operate(String command) {
        System.out.println("device.Light: " + command);
    }
}
