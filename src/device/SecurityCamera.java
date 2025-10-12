package device;

public class SecurityCamera implements Device {
    @Override
    public void operate(String command) {
        System.out.println("Security Camera: " + command);
    }
}
