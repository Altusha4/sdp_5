package decorator;
import device.Device;

public class EnergySavingDecorator extends DeviceDecorator {

    public EnergySavingDecorator(Device device) {
        super(device);
    }

    @Override
    public String name() {
        return device.name() + " (Eco)";
    }

    @Override
    public void operate(String command) {
        if (command.toLowerCase().contains("brightness=100")) {
            command = command.replace("100", "70");
            System.out.println("[Eco Mode] Brightness limited to 70%");
        }
        if (command.toLowerCase().contains("volume=100")) {
            command = command.replace("100", "70");
            System.out.println("[Eco Mode] Volume limited to 70%");
        }
        device.operate(command);
    }
}
